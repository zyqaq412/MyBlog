package com.hzy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: CategoryVo
 * @Author zxwyhzy
 * @Date: 2022/12/18 17:22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {
    private long id;
    private String name;
    //描述
    private String description;

}
