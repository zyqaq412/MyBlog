package com.hzy.controller;

import com.hzy.annotion.SystemLog;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.Article;
import com.hzy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @title: ArticleController
 * @Author zxwyhzy
 * @Date: 2022/12/15 16:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    /*@GetMapping("list")
    public List<Article> test(){

        return articleService.list();

    }*/

    /**
     * 查询热门文章
     * @return
     */
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        // 查询热门文章 封装成ResponseResult
        ResponseResult result =  articleService.hotArticleList();
        return result;
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    /**
     * 查看原文 根据id查找文章
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }


    /**
     * 更新浏览量
     * @param id
     * @return
     */
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }

    /**
     *  文章搜索
     * @param queryString 搜索内容
     * @return 搜索结果
     */
    @GetMapping("/search")
    public ResponseResult searchArticle(@RequestParam("queryString") String queryString) {
        if ("".equals(queryString)){
            return ResponseResult.okResult();
        }
        return articleService.searchArticle(queryString);
    }
}
