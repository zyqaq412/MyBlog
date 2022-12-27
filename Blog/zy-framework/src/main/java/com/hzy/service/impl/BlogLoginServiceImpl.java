package com.hzy.service.impl;

import com.hzy.domain.LoginUser;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.User;
import com.hzy.domain.vo.BlogUserLoginVo;
import com.hzy.domain.vo.UserInfoVo;
import com.hzy.service.BlogLoginService;
import com.hzy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.hzy.utils.*;

import java.util.Objects;

/**
 * @title: BlogLoginServiceImpl
 * @Author zxwyhzy
 * @Date: 2022/12/27 20:29
 * @Version 1.0
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken
                authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        // authenticate() 认证方法
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认证通过 authenticate() 认证失败不会继续执行

        /*if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }*/

        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);

        //把token和userinfo封装 返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid
        Long userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.okResult();
    }
}
