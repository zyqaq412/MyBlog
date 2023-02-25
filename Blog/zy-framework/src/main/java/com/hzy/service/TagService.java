package com.hzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.TagListDto;
import com.hzy.domain.entity.Tag;
import com.hzy.domain.vo.PageVo;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 20:43:11
 */
public interface TagService extends IService<Tag> {


    /**
     *  分页查询标签
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag addTag);
}

