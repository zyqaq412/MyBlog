package com.hzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.constants.SystemConstants;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.AddArticleDto;
import com.hzy.domain.dto.AdminArticleDto;
import com.hzy.domain.dto.ArticleDto;
import com.hzy.domain.entity.Article;
import com.hzy.domain.entity.ArticleTag;
import com.hzy.domain.entity.Category;
import com.hzy.domain.vo.*;
import com.hzy.mapper.ArticleMapper;
import com.hzy.service.ArticleService;
import com.hzy.service.ArticleTagService;
import com.hzy.service.CategoryService;
import com.hzy.utils.BeanCopyUtils;
import com.hzy.utils.RedisCache;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult hotArticleList() {

        // 查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多只查询10条
        Page<Article> page = new Page(1, 10);
        page(page, queryWrapper);

        List<Article> articles = page.getRecords();

        List<HotArticleVo> hotArticleVos =
                BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

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
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);

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

        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount", id.toString(), 1);
        return ResponseResult.okResult();
    }

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public ResponseResult addArticle(AddArticleDto articleDto){
        if (articleDto.getExecuteTime()>System.currentTimeMillis()){
            // 需要定时发布
            // 添加到redis延迟队列
            redisCache.zAdd(SystemConstants.REDIS_TASK_KEY,
                    JSONObject.toJSONString(articleDto),
                    articleDto.getExecuteTime());
            return ResponseResult.okResult();

        }else {
            return add(articleDto);
        }

    }

    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        //添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);


        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());

        //添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getAllArticleList(Integer pageNum, Integer pageSize, ArticleDto articleDto) {
//        1.根据文章标题(模糊查询)和摘要进行查询
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(articleDto.getTitle()), Article::getTitle, articleDto.getTitle());
        queryWrapper.like(StringUtils.hasText(articleDto.getSummary()), Article::getSummary, articleDto.getSummary());
//        2.规定文章是未发布状态不能显示
        // queryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
//        3.分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

//        3.将当前页中的Article对象转换为ArticleDetailsVo对象
        List<Article> articles = page.getRecords();
        List<ArticleDetailsVo> articleDetailsVos = BeanCopyUtils.copyBeanList(articles, ArticleDetailsVo.class);
//        4.将LinkVo对象转换为LinkAdminVo对象
        AdminArticleVo adminArticleVo = new AdminArticleVo(articleDetailsVos, page.getTotal());
        return ResponseResult.okResult(adminArticleVo);
    }

    @Override
    public ResponseResult getArticleById(Long id) {
        Article article = getById(id);
        UpdateArticleVo updateArticleVo = BeanCopyUtils.copyBean(article, UpdateArticleVo.class);
        // 根据文章id获取到文章标签列表
        List<Long> tagList = articleTagService.getTagList(id);
        updateArticleVo.setTags(tagList);
        return ResponseResult.okResult(updateArticleVo);
    }

    @Override
    public ResponseResult updateArticleInfo(AdminArticleDto adminArticleDto) {

        // 1.将AdminArticleDto对象转换为Article对象
        Article article = BeanCopyUtils.copyBean(adminArticleDto, Article.class);
        //  2.将博客的标签信息存入标签表
//          2.1根据当前博客id获取到已有的标签列表
        List<Long> tagList = articleTagService.getTagList(article.getId());
//          2.2得到修改过后的标签列表
        List<Long> tags = article.getTags();
//          2.3遍历修改过后的标签列表，判断当前博客是否已经有此标签，没有则一条数据添加到sg_article_tag表中
        for (Long tag : tags) {
            if (!tagList.contains(tag)) {
                articleTagService.save(new ArticleTag(article.getId(), tag));
            }
        }
        updateById(article);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteArticleById(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public String getNameById(Long id) {
        Article a = getById(id);
        return a.getTitle();
    }

    @Override
    public ResponseResult searchArticle(String queryString) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(queryString), Article::getTitle, queryString)
                .or()
                .like(StringUtils.hasText(queryString), Article::getSummary, queryString);
        List<Article> resultList = articleMapper.selectList(queryWrapper);
        List<searchArticleVo> res = resultList.stream()
                .map(article -> new searchArticleVo(article.getId(), article.getTitle()))
                .collect(Collectors.toList());
        return ResponseResult.okResult(res);
    }

}
