package com.hzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.domain.entity.RoleMenu;


/**
 * 角色和菜单关联表(RoleMenu)表服务接口
 *
 * @author makejava
 * @since 2023-02-27 14:51:35
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     *  根据角色id 删除角色菜单关联表的数据
     * @param id
     */
    void deleteRoleMenuByRoleId(Long id);
}

