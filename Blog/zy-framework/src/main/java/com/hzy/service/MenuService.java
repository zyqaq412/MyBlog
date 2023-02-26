package com.hzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.MenuDto;
import com.hzy.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 22:43:48
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取用户权限
     *  管理员返回所有权限
     *  其他用户返回拥有权限
     * @param id
     * @return
     */
    List<String> selectPermsByUserId(Long id);

    /**
     *  根据用户id查询菜单栏
     * @param userId
     * @return
     */
    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    ResponseResult getMenuList(String status, String menuName);

    ResponseResult addMenu(MenuDto menuDto);
}

