package com.hzy.domain.vo;

import com.hzy.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @title: RoutersVo
 * @Author zxwyhzy
 * @Date: 2023/2/24 23:42
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {

    private List<Menu> menus;
}
