package com.hzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.constants.SystemConstants;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.RoleDto;
import com.hzy.domain.dto.UpdateRoleDto;
import com.hzy.domain.entity.Role;
import com.hzy.domain.entity.RoleMenu;
import com.hzy.domain.vo.AdminRoleVo;
import com.hzy.domain.vo.RoleVo;
import com.hzy.enums.AppHttpCodeEnum;
import com.hzy.mapper.RoleMapper;
import com.hzy.service.RoleMenuService;
import com.hzy.service.RoleService;

import com.hzy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 22:47:31
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleService roleService;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult pageRoleList(Integer pageNum, Integer pageSize, RoleDto roleDto) {
        // 根据友链名(模糊查询)和状态进行查询
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(roleDto.getStatus()), Role::getStatus, roleDto.getStatus());
        queryWrapper.like(StringUtils.hasText(roleDto.getRoleName()), Role::getRoleName, roleDto.getRoleName());
        // 要求按照role_sort进行升序排列。
        queryWrapper.orderByDesc(Role::getRoleSort);

        // 分页查询
        Page<Role> page = new Page<>(pageNum,pageSize);
        page(page, queryWrapper);
        // 3.将当前页中的Role对象转换为RoleVo对象
        List<Role> roles = page.getRecords();
        List<RoleVo> roleVos = BeanCopyUtils.copyBeanList(roles, RoleVo.class);
        // 4.将LinkVo对象转换为LinkAdminVo对象
        AdminRoleVo adminRoleVo = new AdminRoleVo(roleVos, page.getTotal());
        return ResponseResult.okResult(adminRoleVo);
    }

    @Override
    public ResponseResult updateStatusById(UpdateRoleDto updateRoleDto) {
        Role role = new Role();
        role.setId(Long.valueOf(updateRoleDto.getRoleId()));
        role.setStatus(String.valueOf(updateRoleDto.getStatus()));
        updateById(role);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult addRole(Role role) {
        save(role);
        if(role.getMenuIds()!=null&&role.getMenuIds().length>0){
            insertRoleMenu(role);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateRole(Role role) {
        if (!StringUtils.hasText(role.getRoleName())
        ||!StringUtils.hasText(role.getRoleKey())
        ||!StringUtils.hasText(String.valueOf(role.getRoleSort()))){
            return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_MENU_NULL);
        }
        updateById(role);
        // 删除角色菜单关联表的数据重新插入
        roleMenuService.deleteRoleMenuByRoleId(role.getId());
        insertRoleMenu(role);
        return ResponseResult.okResult();
    }

    @Override
    public List<Role> selectRoleAll() {
        return list(Wrappers.<Role>lambdaQuery().eq(Role::getStatus, SystemConstants.NORMAL));
    }

    @Override
    public List<Long> selectRoleIdByUserId(Long userId) {
        return getBaseMapper().selectRoleIdByUserId(userId);
    }

    /**
     *  角色与菜单关联表插入数据
     * @param role
     */
    private void insertRoleMenu(Role role) {
        List<RoleMenu> roleMenuList = Arrays.stream(role.getMenuIds())
                .map(memuId -> new RoleMenu(role.getId(), memuId))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenuList);
    }
    /**
     * 删除角色
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseResult remove(@PathVariable(name = "id") Long id) {
        roleService.removeById(id);
        return ResponseResult.okResult();
    }
}
