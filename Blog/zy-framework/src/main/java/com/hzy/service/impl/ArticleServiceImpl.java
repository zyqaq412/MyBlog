package com.hzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.constants.SystemConstants;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.Article;
import com.hzy.domain.vo.HotArticleVo;
import com.hzy.mapper.ArticleMapper;
import com.hzy.service.ArticleService;
import com.hzy.utils.BeanCopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @title: ArticleServiceImpl
 * @Author zxwyhzy
 * @Date: 2022/12/15 16:50
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
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
}
