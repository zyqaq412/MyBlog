package com.hzy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @title: CommentVo
 * @Author zxwyhzy
 * @Date: 2023/2/18 22:52
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {

    // 头像地址
    private String avatar;
    private Long id;
    //文章id
    private Long articleId;
    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //所回复的目标评论的userid
    private Long toCommentUserId;
    private String toCommentUserName;
    //回复目标评论id
    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    private String username;

    private List<CommentVo> children;
}

