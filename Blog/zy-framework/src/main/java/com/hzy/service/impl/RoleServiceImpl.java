package com.hzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.RoleDto;
import com.hzy.domain.dto.UpdateRoleDto;
import com.hzy.domain.entity.Article;
import com.hzy.domain.entity.Role;
import com.hzy.domain.vo.AdminRoleVo;
import com.hzy.domain.vo.RoleVo;
import com.hzy.mapper.RoleMapper;
import com.hzy.service.RoleService;

import com.hzy.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 22:47:31
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult pageRoleList(Integer pageNum, Integer pageSize, RoleDto roleDto) {
        // 根据友链名(模糊查询)和状态进行查询
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(roleDto.getStatus()), Role::getStatus, roleDto.getStatus());
        queryWrapper.like(StringUtils.hasText(roleDto.getRoleName()), Role::getRoleName, roleDto.getRoleName());
        // 要求按照role_sort进行升序排列。
        queryWrapper.orderByDesc(Role::getRoleSort);

        // 分页查询
        Page<Role> page = new Page<>(pageNum,pageSize);
        page(page, queryWrapper);
        // 3.将当前页中的Role对象转换为RoleVo对象
        List<Role> roles = page.getRecords();
        List<RoleVo> roleVos = BeanCopyUtils.copyBeanList(roles, RoleVo.class);
        // 4.将LinkVo对象转换为LinkAdminVo对象
        AdminRoleVo adminRoleVo = new AdminRoleVo(roleVos, page.getTotal());
        return ResponseResult.okResult(adminRoleVo);
    }

    @Override
    public ResponseResult updateStatusById(UpdateRoleDto updateRoleDto) {
        Role role = new Role();
        role.setId(Long.valueOf(updateRoleDto.getRoleId()));
        role.setStatus(String.valueOf(updateRoleDto.getStatus()));
        updateById(role);
        return ResponseResult.okResult();
    }
}
