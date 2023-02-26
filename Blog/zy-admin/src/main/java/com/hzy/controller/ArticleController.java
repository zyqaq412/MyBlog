package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.AddArticleDto;
import com.hzy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: ArticleController
 * @Author zxwyhzy
 * @Date: 2023/2/26 16:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     *  发布文章
     * @param article
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }


}
