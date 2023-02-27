package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.RoleDto;
import com.hzy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: RoleController
 * @Author zxwyhzy
 * @Date: 2023/2/27 13:08
 * @Version 1.0
 */

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @GetMapping("/list")
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, RoleDto roleDto){
        return roleService.pageRoleList(pageNum,pageSize,roleDto);
    }
}
