package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.AddArticleDto;
import com.hzy.domain.dto.AdminArticleDto;
import com.hzy.domain.dto.ArticleDto;
import com.hzy.domain.vo.UpdateArticleVo;
import com.hzy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return articleService.addArticle(article);
    }

    /**
     * 查询博客列表
     *
     * @param pageNum    页面num
     * @param pageSize   页面大小
     * @param articleDto 文章dto
     * @return {@link ResponseResult}
     */
    @GetMapping("/list")
    public ResponseResult getAllArticleList(Integer pageNum, Integer pageSize, ArticleDto articleDto) {
        return articleService.getAllArticleList(pageNum, pageSize, articleDto);
    }

    /**
     *  修改文章：根据id获取文章详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    /**
     *  修改文章: 更新文章
     * @param article
     * @return
     */
    @PutMapping
    public ResponseResult updateArticleById(@RequestBody AdminArticleDto article){

        return articleService.updateArticleInfo(article);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteArticleById(@PathVariable("id") Long id){
        return articleService.deleteArticleById(id);
    }
}
