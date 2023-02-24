package com.hzy.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.domain.entity.Tag;
import com.hzy.mapper.TagMapper;
import com.hzy.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 20:43:13
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
