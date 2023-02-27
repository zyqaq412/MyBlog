package com.hzy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @title: RoleMenuTreeSelectVo
 * @Author zxwyhzy
 * @Date: 2023/2/27 16:04
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuTreeSelectVo {

    private List<Long> checkedKeys;

    private List<MenuTreeVo> menus;

}
