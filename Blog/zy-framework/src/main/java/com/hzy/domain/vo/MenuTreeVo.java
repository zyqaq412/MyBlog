package com.hzy.domain.vo;

import com.hzy.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @title: AddRoleMenuVo
 * @Author zxwyhzy
 * @Date: 2023/2/27 15:10
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MenuTreeVo {

    private Long id;

    private String label;

    private Long parentId;

    private List<MenuTreeVo> children;
}
