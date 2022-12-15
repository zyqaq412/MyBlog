package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.Article;
import com.hzy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title: ArticleController
 * @Author zxwyhzy
 * @Date: 2022/12/15 16:54
 * @Version 1.0
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    /*@GetMapping("list")
    public List<Article> test(){

        return articleService.list();

    }*/

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        // 查询热门文章 封装成ResponseResult
        ResponseResult result =  articleService.hotArticleList();
        return result;
    }

}
