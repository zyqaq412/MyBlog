package com.hzy.job;

import com.alibaba.fastjson.JSONObject;
import com.hzy.constants.SystemConstants;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.AddArticleDto;
import com.hzy.domain.dto.ArticleDto;
import com.hzy.domain.entity.Article;
import com.hzy.service.ArticleService;
import com.hzy.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @title: ScheduledRelease
 * @Author zxwyhzy
 * @Date: 2023/9/25 17:13
 * @Version 1.0
 */
@Component
public class ScheduledRelease {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    // 每分钟获取一次到时的文章
    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduledAddArticle(){
        Set<Object> sets = redisCache
                .getMembersInRangeByScore(SystemConstants.REDIS_TASK_KEY, 0, System.currentTimeMillis());
        for (Object o : sets){
            AddArticleDto articleDto = (AddArticleDto) o;
            ResponseResult ans = articleService.add(articleDto);
            if (ans.getCode() == 200 ){
                redisCache.zRemove(SystemConstants.REDIS_TASK_KEY, JSONObject.toJSONString(articleDto));
            }

        }
    }
}
