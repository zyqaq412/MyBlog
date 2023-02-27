package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.domain.entity.UserRole;
import com.hzy.mapper.UserRoleMapper;
import com.hzy.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2023-02-27 16:47:41
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
