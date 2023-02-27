package com.hzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.Category;
import com.hzy.domain.vo.CategoryVo;
import com.hzy.domain.vo.PageVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-12-18 17:12:13
 */
public interface CategoryService extends IService<Category> {

    /**
     * 根据分类查询文章
     * @return
     */
    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);
}

