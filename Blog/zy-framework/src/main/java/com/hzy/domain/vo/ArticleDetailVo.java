package com.hzy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @title: ArticleDetailVo
 * @Author zxwyhzy
 * @Date: 2022/12/18 19:53
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {

    // 所属分类id
    private Long categoryId;
    //所属分类名
    private String categoryName;

    //文章内容
    private String content;

    private Date createTime;

    private Long id;

    //是否允许评论 1是，0否
    private String isComment;

    //标题
    private String title;

    //访问量
    private Long viewCount;

}
