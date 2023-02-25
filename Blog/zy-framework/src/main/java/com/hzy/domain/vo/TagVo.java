package com.hzy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: TagVo
 * @Author zxwyhzy
 * @Date: 2023/2/25 23:51
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagVo {

    private Long id;

    //标签名
    private String name;

    //备注
    private String remark;

}
