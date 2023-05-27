package com.hzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.AddArticleDto;
import com.hzy.domain.dto.AdminArticleDto;
import com.hzy.domain.dto.ArticleDto;
import com.hzy.domain.entity.Article;
import com.hzy.domain.vo.UpdateArticleVo;

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

    /**
     *  后台文章管理  查询文章
     * @param pageNum
     * @param pageSize
     * @param articleDto
     * @return
     */
    ResponseResult getAllArticleList(Integer pageNum, Integer pageSize, ArticleDto articleDto);

    /**
     *  根据id查询文章
     * @param id
     * @return
     */
    ResponseResult getArticleById(Long id);

    ResponseResult updateArticleInfo(AdminArticleDto article);

    ResponseResult deleteArticleById(Long id);

    /**
     *  根据id获取文章名
     * @param id 文章id
     * @return 文章名
     */

    String getNameById(Long id);

    ResponseResult searchArticle(String queryString);
}
