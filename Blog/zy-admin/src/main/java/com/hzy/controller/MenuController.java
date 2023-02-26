package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.MenuDto;
import com.hzy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public ResponseResult addMenu(MenuDto menuDto){
        return menuService.addMenu(menuDto);

    }
}
