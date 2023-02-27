package com.hzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.constants.SystemConstants;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.MenuDto;
import com.hzy.domain.entity.Menu;
import com.hzy.domain.vo.MenuTreeVo;
import com.hzy.domain.vo.MenuVo;
import com.hzy.enums.AppHttpCodeEnum;
import com.hzy.mapper.MenuMapper;
import com.hzy.service.MenuService;
import com.hzy.utils.BeanCopyUtils;
import com.hzy.utils.SecurityUtils;
import com.hzy.utils.SystemConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 22:43:49
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，返回所有的权限
        if(SecurityUtils.isAdmin()){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType,SystemConstants.MENU,SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        //否则返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否是管理员
        if(SecurityUtils.isAdmin()){
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        }else{
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus,0L);
        return menuTree;
    }

    @Override
    public ResponseResult getMenuList(String status, String menuName) {
        //        1.根据menu状态和menuName查询
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(status), Menu::getStatus, status)
                .like(StringUtils.hasText(menuName), Menu::getMenuName, menuName)
                .orderByAsc(Menu::getParentId, Menu::getOrderNum);
        List<Menu> menus = list(queryWrapper);
//        2.将List<Menu>对象转换为List<MenuVo>对象
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        return ResponseResult.okResult(menuVos);
    }

    @Override
    public ResponseResult addMenu(MenuDto menuDto) {
        //        1.将MenuDto对象转换为Menu对象
        Menu menu = BeanCopyUtils.copyBean(menuDto, Menu.class);
//        2.根据MenuName判断当前是否存在menu
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getMenuName, menu.getMenuName());
        Menu oneMenu = getOne(queryWrapper);
        if (!Objects.isNull(oneMenu)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.ADD_MENU_FAIL);
        }
        save(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMenuById(Long id) {
        Menu menu = getById(id);
        MenuVo menuVo = BeanCopyUtils.copyBean(menu, MenuVo.class);
        return ResponseResult.okResult(menuVo);
    }

    @Override
    public ResponseResult updateMenu(MenuDto menuDto) {
        //        1.判断menuDto对象值是否为空
        if (!StringUtils.hasText(menuDto.getMenuName()) ||
                !StringUtils.hasText(menuDto.getMenuType()) ||
                !StringUtils.hasText(String.valueOf(menuDto.getStatus())) ||
                !StringUtils.hasText(menuDto.getPath()) ||
                !StringUtils.hasText(String.valueOf(menuDto.getOrderNum())) ||
                !StringUtils.hasText(menuDto.getIcon())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_MENU_NULL);
        }
        if (menuDto.getId() == menuDto.getParentId()){
            return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_MENU_CF);
        }
        //1.将MenuDto对象转化为Menu对象
        Menu menu = BeanCopyUtils.copyBean(menuDto, Menu.class);
        updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenuById(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMenuTree() {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = menuMapper.selectAllRouterMenu();
        List<MenuTreeVo> menuTreeVos =  SystemConverter.buildMenuSelectTree(menus);
        return ResponseResult.okResult(menuTreeVos);
    }

    @Override
    public List<Menu> selectMenuList(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        //menuName模糊查询
        queryWrapper.like(StringUtils.hasText(menu.getMenuName()),Menu::getMenuName,menu.getMenuName());
        queryWrapper.eq(StringUtils.hasText(menu.getStatus()),Menu::getStatus,menu.getStatus());
        //排序 parent_id和order_num
        queryWrapper.orderByAsc(Menu::getParentId,Menu::getOrderNum);
        List<Menu> menus = list(queryWrapper);;
        return menus;
    }

    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        return getBaseMapper().selectMenuListByRoleId(roleId);
    }

    /**
     *  查出一级菜单，将子菜单赋值给children
     *  将查出的菜单转成树状
     * @param menus 菜单集合
     * @param parentId 一级菜单id
     * @return
     */
    private List<Menu> builderMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
        /*
        new Function<Menu, Object>() {
                    @Override
                    public Object apply(Menu menu) {
                    因为 setChildren 方法没有返回值 apply方法需要返回值 所以会报错
                    需要在Menu中开启链式编程 @Accessors(chain = true)
                        return menu.setChildren(getChildren(menu, menus);
                    }
                })
        */
    }

    /**
     *  获取子菜单
     * 获取存入参数的 子Menu集合
     * @param menu 一级菜单
     * @param menus 菜单集合
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
        return childrenList;
    }
}
