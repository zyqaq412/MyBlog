package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.MenuDto;
import com.hzy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
