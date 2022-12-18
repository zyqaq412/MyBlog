package com.hzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-12-18 20:18:13
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

