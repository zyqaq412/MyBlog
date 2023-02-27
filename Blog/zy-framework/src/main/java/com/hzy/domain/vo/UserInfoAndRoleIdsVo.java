package com.hzy.domain.vo;

import com.hzy.domain.entity.Role;
import com.hzy.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @title: aa
 * @Author zxwyhzy
 * @Date: 2023/2/27 16:57
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoAndRoleIdsVo {
    private User user;
    private List<Role> roles;
    private List<Long> roleIds;



}
