package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.Comment;
import com.hzy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @title: CommentController
 * @Author zxwyhzy
 * @Date: 2023/2/18 22:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     *
     * @param articleId 文章id
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);

    }
}
