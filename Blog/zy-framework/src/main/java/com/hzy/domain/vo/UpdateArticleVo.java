package com.hzy.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @title: UpdateArticleVo
 * @Author zxwyhzy
 * @Date: 2023/2/26 21:29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArticleVo {
    private Long id;

    private String title;

    private String summary;

    private String thumbnail;

    private Long viewCount;


    private Date createTime;


    private Date updateTime;

    private String isComment;

    private String updateBy;

    private String createBy;

    private String isTop;

    private String status;

    private Long categoryId;

    private String content;

    private Integer delFlag;

    private List<Long> tags;

}
