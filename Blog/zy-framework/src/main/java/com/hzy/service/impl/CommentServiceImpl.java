package com.hzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.constants.SystemConstants;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.Comment;
import com.hzy.domain.vo.CommentVo;
import com.hzy.domain.vo.PageVo;
import com.hzy.enums.AppHttpCodeEnum;
import com.hzy.exception.SystemException;
import com.hzy.mapper.CommentMapper;
import com.hzy.service.CommentService;

import com.hzy.service.UserService;
import com.hzy.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-02-17 22:44:58
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对articleId进行判断
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);
        //根评论 rootId为-1
        queryWrapper.eq(Comment::getRootId,-1);

        //评论类型
        queryWrapper.eq(Comment::getType,commentType);

        //分页查询
        Page<Comment> page = new Page(pageNum,pageSize);
        page(page,queryWrapper);

        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        for (CommentVo vo : commentVoList) {
            // 添加头像路径
            if (null == userService.getById(vo.getCreateBy())){
                vo.setAvatar("http://rqiojt6a0.hn-bkt.clouddn.com/2023/03/04/2f85e288daba43aeb35f886e2294ed89.png");
            }else if (null == userService.getById(vo.getCreateBy()).getAvatar()){
                vo.setAvatar("http://rqiojt6a0.hn-bkt.clouddn.com/2023/03/04/b2c341da7b674917bb079fc5648b5af6.png");
            }else {
                vo.setAvatar(userService.getById(vo.getCreateBy()).getAvatar());
            }

        }

        //查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }
    /*@Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对于文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        // 对articleId进行判断
        queryWrapper.eq(Comment::getArticleId,articleId);
        //根评论rootId为-1
        queryWrapper.eq(Comment::getRootId,-1);
        //分页查询
        Page<Comment> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

       // List<CommentVo> commentVoList = BeanCopyUtil.copyBeanList(page.getRecords(),CommentVo.class);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        // 查询对于的子评论集合，并赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            // 查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }*/

    /**
     * 添加评论到数据库
     * @param comment
     * @return
     */
    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 根据根评论的id查询所对应的字评论的集合
     * @param id 根评论id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(comments);
        for (CommentVo vo : commentVos) {
            // 添加头像路径
            if (null == userService.getById(vo.getCreateBy())){
                vo.setAvatar("http://rqiojt6a0.hn-bkt.clouddn.com/2023/03/04/2f85e288daba43aeb35f886e2294ed89.png");
            }else if (null == userService.getById(vo.getCreateBy()).getAvatar()){
                vo.setAvatar("http://rqiojt6a0.hn-bkt.clouddn.com/2023/03/04/b2c341da7b674917bb079fc5648b5af6.png");
            }else {
                vo.setAvatar(userService.getById(vo.getCreateBy()).getAvatar());
            }

        }
        return commentVos;

    }

    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历vo集合
        for (CommentVo commentVo : commentVos) {
            if(commentVo.getCreateBy() == -1){
                commentVo.setUsername("游客");
            }else {
                //通过creatyBy查询用户的昵称并赋值
                // 添加try catch 预防用户被删除查询昵称失败
                try {
                    String nickName =
                            userService.getById(commentVo.getCreateBy()).getNickName();
                    commentVo.setUsername(nickName);
                }catch (Exception e){
                    commentVo.setUsername("已注销用户");
                }


            }

            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询
            if(commentVo.getToCommentUserId()!=-1){
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }
}
