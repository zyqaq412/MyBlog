package com.hzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-02-18 23:08:43
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult selectUserPage(User user, Integer pageNum, Integer pageSize);

    /**
     *  用户名是否存在
     * @param userName
     * @return
     */
    boolean checkUserNameUnique(String userName);

    /**
     *  电话号是否存在
     * @param user
     * @return
     */
    boolean checkPhoneUnique(User user);

    /**
     *  邮箱是否存在
     * @param user
     * @return
     */
    boolean checkEmailUnique(User user);

    ResponseResult addUser(User user);

    void updateUser(User user);
}

