package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.RoleDto;
import com.hzy.domain.dto.UpdateRoleDto;
import com.hzy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     *  分页查询角色列表
     * @param pageNum
     * @param pageSize
     * @param roleDto
     * @return
     */
    @GetMapping("/list")
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, RoleDto roleDto){
        return roleService.pageRoleList(pageNum,pageSize,roleDto);
    }

    @PutMapping("/changeStatus")
    public ResponseResult updateStatusById(@RequestBody UpdateRoleDto updateRoleDto){
        return roleService.updateStatusById(updateRoleDto);
    }
}
