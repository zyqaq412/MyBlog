package com.hzy.utils;

import com.hzy.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @title: SecurityUtils
 * @Author zxwyhzy
 * @Date: 2023/2/21 22:30
 * @Version 1.0
 */
public class SecurityUtils {

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        // 定时任务执行时 更新文章表 文章表有自动填充的字段
        // 需要获取登录用户插入到updateBy
        // 执行时没有登录用户会报空指针异常

        if (!Objects.isNull(getAuthentication())) {
            if (!getAuthentication().getPrincipal().equals("anonymousUser")) {
                return (LoginUser) getAuthentication().getPrincipal();
            }
        }
        return null;
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {
        if (!Objects.isNull(getLoginUser())) {
            Long id = getLoginUser().getUser().getId();
            return id != null && id.equals(1L);
        }
        return false;
    }

    public static Long getUserId() {
        if (!Objects.isNull(getLoginUser())) {
            return getLoginUser().getUser().getId();
        }
        return null;
    }
}
