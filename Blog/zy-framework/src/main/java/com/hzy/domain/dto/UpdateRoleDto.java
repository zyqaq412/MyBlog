package com.hzy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: UpdateRoleDto
 * @Author zxwyhzy
 * @Date: 2023/2/27 14:29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleDto {
    Integer roleId;
    Integer status;
}
