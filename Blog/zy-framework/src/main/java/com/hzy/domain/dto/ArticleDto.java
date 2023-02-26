package com.hzy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: ArticleDto
 * @Author zxwyhzy
 * @Date: 2023/2/26 21:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private String title;
    private String summary;
}
