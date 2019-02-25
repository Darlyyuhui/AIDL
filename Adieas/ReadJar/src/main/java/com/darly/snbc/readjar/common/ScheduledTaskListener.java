package com.darly.snbc.readjar.common;

/**
 * 包名称：com.darly.snbc.readjar.common
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/2/21 11:36
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface ScheduledTaskListener {


    //柜门闸门查询间隔时间，3秒
   void doorPoolTime (String name);
    //整机状态查询间隔时间，3秒
   void workPoolTime (String name);
    //心跳查询间隔时间，60秒
   void heartPoolTime(String name);
    //故障查询间隔时间，3秒
   void errorPoolTime(String name);
    //货道查询间隔时间，10分钟
   void goodsPoolTime(String name);
}
