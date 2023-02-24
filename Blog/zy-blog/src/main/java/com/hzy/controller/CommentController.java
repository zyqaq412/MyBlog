package com.hzy.controller;

import com.hzy.annotion.SystemLog;
import com.hzy.constants.SystemConstants;
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


    /*@GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(articleId,pageNum,pageSize);
    }*/

    /**
     * 查询文章评论
     * @param articleId 文章id
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }


    /**
     * 查询友链评论
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return
     */
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @PostMapping
    @SystemLog(BusinessName = "发送评论")
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);

    }
}
