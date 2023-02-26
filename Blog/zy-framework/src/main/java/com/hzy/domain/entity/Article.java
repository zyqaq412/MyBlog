package com.hzy.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 文章表(Article)表实体类
 *
 * @author makejava
 * @since 2022-12-15 16:44:24
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor// 无参构造
@AllArgsConstructor
@TableName("zy_article")//Table 'zy_blog.article' doesn't exist
public class Article  {
    @TableId
    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;
    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    //状态（0已发布，1草稿）
    private String status;
    //访问量
    private Long viewCount;
    //是否允许评论 1是，0否
    private String isComment;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

    // 分页查询 用来装分类名
    @TableField(exist = false)// 该字段在表中不存在
    private String categoryName;

    /**
     *  更新数据库浏览量时需要传递Article对象
     *  redis里面只有文章id和浏览量
     *  所以需要这个构造函数创建对象
     * @param id
     * @param viewCount
     */
    public Article(Long id, long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}

