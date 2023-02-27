package com.hzy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @title: AdminRoleVo
 * @Author zxwyhzy
 * @Date: 2023/2/27 13:41
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleVo {
    private List<RoleVo> rows;
    private Long total;
}
