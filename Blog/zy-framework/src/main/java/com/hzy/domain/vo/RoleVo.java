package com.hzy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @title: RoleVo
 * @Author zxwyhzy
 * @Date: 2023/2/27 13:51
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RoleVo {
    private Long id;

    private String remark;

    private String roleKey;

    private String roleName;

    private Integer roleSort;

    private String createTime;

    private String status;

    private String delFlag;
}