package com.hzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.Article;

import java.sql.Array;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
