package com.hzy.domain.vo;

import lombok.Data;

/**
 * @title: HotArticleVo
 * @Author zxwyhzy
 * @Date: 2022/12/16 15:33
 * @Version 1.0
 */
@Data
public class HotArticleVo {
    // id
    private long id;
    private String title;
    private Long viewCount;
}
