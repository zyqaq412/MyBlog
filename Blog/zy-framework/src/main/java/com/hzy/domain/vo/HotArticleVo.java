package com.hzy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: HotArticleVo
 * @Author zxwyhzy
 * @Date: 2022/12/16 15:33
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {
    // id
    private long id;
    private String title;
    private Long viewCount;
}
