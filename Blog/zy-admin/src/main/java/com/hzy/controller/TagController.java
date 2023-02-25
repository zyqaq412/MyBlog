package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.TagListDto;
import com.hzy.domain.entity.Tag;
import com.hzy.domain.vo.PageVo;
import com.hzy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     *  添加标签
     * @param addTag
     * @return
     */
    @PostMapping
    public ResponseResult addTag(@RequestBody Tag addTag){
        return tagService.addTag(addTag);
    }

    /**
     *  删除标签 逻辑删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable("id") Long id){
       return tagService.deleteTagById(id);

    }
}
