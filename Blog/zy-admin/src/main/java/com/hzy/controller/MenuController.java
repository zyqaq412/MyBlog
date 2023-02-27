package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.MenuDto;
import com.hzy.domain.entity.Menu;
import com.hzy.domain.vo.MenuTreeVo;
import com.hzy.domain.vo.RoleMenuTreeSelectVo;
import com.hzy.service.MenuService;
import com.hzy.utils.SystemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @title: MenuController
 * @Author zxwyhzy
 * @Date: 2023/2/26 21:58
 * @Version 1.0
 */
@RestController
@RequestMapping("system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     *  查询菜单
     * @param status 状态
     * @param menuName 菜单名
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getMenuList(String status, String menuName){
        return menuService.getMenuList(status,menuName);
    }

    /**
     *  添加菜单
     * @param menuDto
     * @return
     */
    @PostMapping
    public ResponseResult addMenu(MenuDto menuDto){
        return menuService.addMenu(menuDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getMenuById(@PathVariable("id") Long id){
       return menuService.getMenuById(id);
    }

    /**
     *  更新菜单
     * @param menuDto
     * @return
     */
    @PutMapping
    public ResponseResult updateMenu(@RequestBody MenuDto menuDto){
        return menuService.updateMenu(menuDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteMenuById(@PathVariable("id") Long id){
       return  menuService.deleteMenuById(id);
    }

    /**
     *  获取菜单树
     * @return
     */
    @GetMapping("/treeselect")
    public ResponseResult getMenuTree(){
       return menuService.getMenuTree();
    }
    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public ResponseResult roleMenuTreeSelect(@PathVariable("roleId") Long roleId) {
        List<Menu> menus = menuService.selectMenuList(new Menu());
        List<Long> checkedKeys = menuService.selectMenuListByRoleId(roleId);
        List<MenuTreeVo> menuTreeVos = SystemConverter.buildMenuSelectTree(menus);
        RoleMenuTreeSelectVo vo = new RoleMenuTreeSelectVo(checkedKeys,menuTreeVos);
        return ResponseResult.okResult(vo);
    }
}
