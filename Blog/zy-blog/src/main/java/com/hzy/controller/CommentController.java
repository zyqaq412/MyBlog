package com.hzy.controller;

import com.hzy.annotion.SystemLog;
import com.hzy.constants.SystemConstants;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.AddCommentDto;
import com.hzy.domain.entity.Comment;
import com.hzy.enums.AppHttpCodeEnum;
import com.hzy.exception.SystemException;
import com.hzy.service.CommentService;
import com.hzy.utils.BeanCopyUtils;
import com.hzy.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @title: CommentController
 * @Author zxwyhzy
 * @Date: 2023/2/18 22:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论",description = "评论相关接口")
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
    @ApiOperation(value = "友链评论列表",notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }

    /**
     * 添加评论
     * @param addCommentDto
     * @return
     */
    @PostMapping
    @SystemLog(BusinessName = "发送评论")
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto){
       try {
           if (Objects.isNull(SecurityUtils.getLoginUser())){
           }
           Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
           return commentService.addComment(comment);
       }catch (Exception e){
           return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
       }



    }
}
