package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.vo.CategoryVo;
import com.hzy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title: CategoryController
 * @Author zxwyhzy
 * @Date: 2023/2/26 16:25
 * @Version 1.0
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }


}
