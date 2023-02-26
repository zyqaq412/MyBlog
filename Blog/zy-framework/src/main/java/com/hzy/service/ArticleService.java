package com.hzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.AddArticleDto;
import com.hzy.domain.entity.Article;

import java.sql.Array;

public interface ArticleService extends IService<Article> {
    /**
     * 查询热门文章
     * @return
     */
    ResponseResult hotArticleList();

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    /**
     * 查看原文
     * @param id
     * @return
     */
    ResponseResult getArticleDetail(Long id);

    /**
     * 更新浏览量
     * @param id 文章id
     * @return
     */
    ResponseResult updateViewCount(Long id);

    /**
     *  添加文章
     * @param article
     * @return
     */
    ResponseResult add(AddArticleDto article);
}
