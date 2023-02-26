package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.domain.entity.ArticleTag;
import com.hzy.mapper.ArticleTagMapper;
import com.hzy.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-02-26 16:55:31
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
