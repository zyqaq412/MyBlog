package com.hzy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: RoleDto
 * @Author zxwyhzy
 * @Date: 2023/2/27 13:11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    //角色名称
    private String roleName;
    //角色状态（0正常 1停用）
    private String status;
}
