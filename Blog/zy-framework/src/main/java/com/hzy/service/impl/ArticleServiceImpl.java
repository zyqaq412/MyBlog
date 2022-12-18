package com.hzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.constants.SystemConstants;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.Article;
import com.hzy.domain.entity.Category;
import com.hzy.domain.vo.ArticleListVo;
import com.hzy.domain.vo.HotArticleVo;
import com.hzy.domain.vo.PageVo;
import com.hzy.mapper.ArticleMapper;
import com.hzy.service.ArticleService;
import com.hzy.service.CategoryService;
import com.hzy.utils.BeanCopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @title: ArticleServiceImpl
 * @Author zxwyhzy
 * @Date: 2022/12/15 16:50
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private CategoryService categoryService;
    @Override
    public ResponseResult hotArticleList() {

        // 查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多只查询10条
        Page<Article> page = new Page(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

        List<HotArticleVo> hotArticleVos =
                BeanCopyUtil.copyBeanList(articles, HotArticleVo.class);

        // 将copy封装到工具类
        /*List<HotArticleVo> articlesVos = new ArrayList<>();

        for (Article article : articles) {
            HotArticleVo hotArticleVo = new HotArticleVo();
            // bean拷贝
            BeanUtils.copyProperties(article,hotArticleVo);

            articlesVos.add(hotArticleVo);
        }*/

        return ResponseResult.okResult(hotArticleVos);

    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0 ,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        // 查询categoryName
        List<Article> articles = page.getRecords();

        articles.stream()
                .map(article -> {
                    // 根据 article的分类id查询到分类名
                    Category category = categoryService.getById(article.getCategoryId());
                    // 将查到的分类名 赋值给article
                    article.setCategoryName(category.getName());
                    // 返回赋值后的article
                    return article;
                })
                // 在Article类添加注解Accessors(chain=true) 之后 set方法不再返回void而是对象本身 就可以用下列的.map写法
                //.map(article ->
                // article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))

                // 终结操作随便写
                //.collect(Collectors.toList());
                .count();


        /*for (Article article : articles) {
            // 根据 article的分类id查询到分类名
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }*/

        List<ArticleListVo> articleListVos = BeanCopyUtil.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

}
