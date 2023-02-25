package com.hzy.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.domain.LoginUser;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.TagListDto;
import com.hzy.domain.entity.Tag;
import com.hzy.domain.vo.PageVo;
import com.hzy.domain.vo.TagVo;
import com.hzy.mapper.TagMapper;
import com.hzy.service.TagService;
import com.hzy.utils.BeanCopyUtils;
import com.hzy.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 20:43:13
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(Tag addTag) {
        save(addTag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTagById(Long id) {
        /*Tag tag = new Tag();
        tag.setId(id);
        tag.setDelFlag(1);
        updateById(tag);*/
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTagInfo(Long id) {

        Tag tag = getById(id);

        TagVo tagVo = BeanCopyUtils.copyBean(tag,TagVo.class);

        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult updateTagInfo(Tag tag) {
        updateById(tag);
        return ResponseResult.okResult();
    }
}
