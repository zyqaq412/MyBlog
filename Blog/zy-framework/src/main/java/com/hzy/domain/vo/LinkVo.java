package com.hzy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: LinkVo
 * @Author zxwyhzy
 * @Date: 2022/12/18 20:22
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {
    //网站地址
    private String address;

    private String description;

    private Long id;


    private String name;

    private String logo;
}
