package com.hzy.constants;

/**
 * @title: SystemConstants
 * @Author zxwyhzy
 * @Date: 2022/12/16 15:58
 * @Version 1.0
 */
/*
 * @Date: 2022/12/16 15:59
 * 定义常量类 如果以后需要将 2 设置为草稿 改这里就行了
 */
public class SystemConstants {

        public static final String REDIS_TASK_KEY = "Task";

        /**
         *  文章是草稿
         */
        public static final int ARTICLE_STATUS_DRAFT = 1;
        /**
         *  文章是正常分布状态
         */
        public static final int ARTICLE_STATUS_NORMAL = 0;
        public static final String STATUS_NORMAL = "0";

        /**
         * 友链状态为审核通过
         */
        public static final String  LINK_STATUS_NORMAL = "0";

        /**
         * 评论类型为：文章评论
         */
        public static final String ARTICLE_COMMENT = "0";
        /**
         * 评论类型为：友联评论
         */
        public static final String LINK_COMMENT = "1";

        public static final Object MENU ="C" ;
        public static final Object BUTTON = "F";

        /** 正常状态 */
        public static final String NORMAL = "0";
        public static final String ADMAIN = "1";

}
