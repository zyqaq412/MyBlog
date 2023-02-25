package com.hzy.domain.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @title: TagDto
 * @Author zxwyhzy
 * @Date: 2023/2/25 22:27
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListDto {

    private Long id;

    //标签名
    private String name;

    //备注
    private String remark;

}
