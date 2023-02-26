package com.hzy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @title: AdminArticleVo
 * @Author zxwyhzy
 * @Date: 2023/2/26 21:10
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminArticleVo {
    private List<ArticleDetailsVo> rows;
    private Long total;
}
