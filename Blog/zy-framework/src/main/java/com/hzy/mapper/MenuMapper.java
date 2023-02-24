package com.hzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-24 22:43:45
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     *  获取该用户所拥有的权限
     * @param id
     * @return
     */
    List<String> selectPermsByUserId(Long id);
}
