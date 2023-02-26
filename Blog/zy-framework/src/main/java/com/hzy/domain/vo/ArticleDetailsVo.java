package com.hzy.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @title: ArticleDetailsVo
 * @Author zxwyhzy
 * @Date: 2023/2/26 21:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailsVo {
    /**
     * 文章标题
     */
    private Long id;

    /**
     * 文章分类id
     */
    private String categoryId;

    /**
     * 文章分类
     */
    private String categoryName;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章缩略图
     */
    private String thumbnail;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 浏览量
     */
    private Long viewCount;




}
