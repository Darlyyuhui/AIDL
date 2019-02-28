package com.darly.snbc.readjar.common;

import java.util.List;

/**
 * 状态机定义接口，主要提供接口给调用者使用，在调用端进行广播上报Agent。
 * 包名称：com.darly.snbc.readjar.common
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/2/21 11:36
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface ScheduledTaskListener {

    /**状态暂停，执行完成当前指令后，进行暂停。线程池继续运行。线程内部逻辑不执行。
     * @param msg 输出信息执行到哪步进行暂停。
     */
    void onPauseScheduledTask(String msg);

    /**状态重新启动，线程池继续运行。线程内部逻辑重新开始执行。
     * @param msg 重新开始状态机轮询信息。
     */
    void onReStartScheduledTask(String msg);

    /**柜门闸门查询间隔时间，3秒，回调发送广播通知
     * @param boxIndex 售货机操作的柜机
     * @param senStateTmp 柜门状态
     * @param senZState 闸机状态
     * @param assetCode 资产编码
     * @param vemidValue  售货机编码
     * @param boxIDState 售货机状态
     */
   void doorPoolTime (int boxIndex,int senStateTmp,int senZState, String assetCode,String vemidValue,int boxIDState);

    /**整机状态查询间隔时间，3秒，回调发送广播通知
     * @param boxIndex 售货机操作的柜机
     * @param fgWorkState 整机状态
     * @param assetCode 资产编码
     * @param vemidValue  售货机编码
     * @param boxIDState 售货机状态
     */
   void workPoolTime (int boxIndex,int fgWorkState, String assetCode,String vemidValue,int boxIDState);

    /**故障查询间隔时间，3秒，回调发送广播通知
     * @param boxIndex 售货机操作的柜机
     * @param faultClean 故障模块
     * @param faultReport 故障等级
     * @param assetCode 资产编码
     * @param vemidValue  售货机编码
     * @param boxIDState 售货机状态
     */
   void errorPoolTime(int boxIndex,byte[] faultClean,byte[] faultReport, String assetCode,String vemidValue,int boxIDState);

    /**心跳查询间隔时间，60秒，回调发送广播通知
     * @param boxIndex 售货机操作的柜机
     * @param eheartcode  电源控制板心跳，接口成功返回1，失败返回2.
     * @param zheartcode  主控制板心跳，接口成功返回1，失败返回2.
     * @param assetCode 资产编码
     * @param vemidValue  售货机编码
     * @param boxIDState 售货机状态
     */
    void heartPoolTime(int boxIndex,int eheartcode,int zheartcode, String assetCode,String vemidValue,int boxIDState);

    /**货道查询间隔时间，10分钟
     * @param boxIndex 售货机操作的柜机
     * @param goodsstate 货道状态
     * @param assetCode 资产编码
     * @param vemidValue  售货机编码
     * @param boxIDState 售货机状态
     */
   void goodsPoolTime(int boxIndex,List<Integer> goodsstate, String assetCode, String vemidValue, int boxIDState);

    /**线程池关闭，内部逻辑停止执行。
     * @param msg 输出状态机停止信息。
     */
    void onStopScheduledTask(String msg);

}
