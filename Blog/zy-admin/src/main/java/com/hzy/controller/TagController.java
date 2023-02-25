package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.TagListDto;
import com.hzy.domain.vo.PageVo;
import com.hzy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: TagController
 * @Author zxwyhzy
 * @Date: 2023/2/24 20:46
 * @Version 1.0
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     *  分页查询标签
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }
}
