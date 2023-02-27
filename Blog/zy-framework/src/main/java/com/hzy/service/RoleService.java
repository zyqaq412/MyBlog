package com.hzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.RoleDto;
import com.hzy.domain.dto.UpdateRoleDto;
import com.hzy.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 22:47:31
 */
public interface RoleService extends IService<Role> {

    /**
     *  获取用户角色
     *  管理员返回集合只有 admin
     *  其他用户返回所有角色
     * @param id
     * @return
     */
    List<String> selectRoleKeyByUserId(Long id);


    ResponseResult pageRoleList(Integer pageNum, Integer pageSize, RoleDto roleDto);

    ResponseResult updateStatusById(UpdateRoleDto updateRoleDto);

    ResponseResult addRole(Role role);
}

