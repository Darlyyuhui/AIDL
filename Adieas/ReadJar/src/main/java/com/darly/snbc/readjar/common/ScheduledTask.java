package com.darly.snbc.readjar.common;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 包名称：com.darly.snbc.readjar.common
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/2/21 11:34
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ScheduledTask {

    private ScheduledExecutorService service;

    private ScheduledTaskListener scheduledTaskListener;

    private boolean isScheduledRunning = false;

    private int defaultClock = 0;//默认还原时间。
    private int totalClock = 0;//记录总的轮询时间。
    private static int TOTALSIZE = 6000;//轮询时间间隔,1秒

    private static int COMMONPOOLTIME = 1;//轮询时间间隔,1秒

    private static int DOORPOOLTIME = 3;//柜门闸门查询间隔时间，3秒
    private static int WORKPOOLTIME = 3;//整机状态查询间隔时间，3秒
    private static int HEARTPOOLTIME = 60;//心跳查询间隔时间，60秒
    private static int ERRORPOOLTIME = 3;//故障查询间隔时间，3秒
    private static int GOODSPOOLTIME = 600;//货道查询间隔时间，10分钟

    public void setScheduledTaskListener(ScheduledTaskListener scheduledTaskListener) {
        this.scheduledTaskListener = scheduledTaskListener;
    }

    public void excuteSchedule() {
        if (scheduledTaskListener == null) {
            Log.i(getClass().getSimpleName(), "监听未初始化");
            return;
        }
        if (!isScheduledRunning) {
            service = Executors.newScheduledThreadPool(1);
            service.scheduleAtFixedRate(runnable, 0, COMMONPOOLTIME, TimeUnit.SECONDS);
            isScheduledRunning = true;
        } else {
            Log.i(getClass().getSimpleName(), "任务正在执行，不需要重新启动");
        }
    }

    public void shutDown() {
        if (service != null && !service.isShutdown()) {
            isScheduledRunning = false;
            try {
                service.shutdown();
                if (!service.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                    service.shutdownNow();
                }
            } catch (InterruptedException e) {
                service.shutdownNow();
            }

        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {

                if (totalClock%DOORPOOLTIME !=0){
                    scheduledTaskListener.doorPoolTime(Thread.currentThread()+"--->99："+totalClock);
                }else {
                    Thread.sleep(new Random().nextInt(1000));
                    scheduledTaskListener.doorPoolTime(Thread.currentThread()+"--->进行柜门闸门查询 间隔时间已到："+totalClock);
                }
                if (totalClock%WORKPOOLTIME !=0){
                    scheduledTaskListener.workPoolTime(Thread.currentThread()+"--->99："+totalClock);
                }else {
                    Thread.sleep(new Random().nextInt(1000));
                    scheduledTaskListener.workPoolTime(Thread.currentThread()+"--->进行整机状态查询 间隔时间已到："+totalClock);
                }
                if (totalClock%HEARTPOOLTIME !=0){
                    scheduledTaskListener.heartPoolTime(Thread.currentThread()+"--->99："+totalClock);
                }else {
                    Thread.sleep(new Random().nextInt(1000));
                    scheduledTaskListener.heartPoolTime(Thread.currentThread()+"--->进行心跳查询 间隔时间已到："+totalClock);
                }
                if (totalClock%ERRORPOOLTIME !=0){
                    scheduledTaskListener.errorPoolTime(Thread.currentThread()+"--->99："+totalClock);
                }else {
                    Thread.sleep(new Random().nextInt(1000));
                    scheduledTaskListener.errorPoolTime(Thread.currentThread()+"--->进行故障查询 间隔时间已到："+totalClock);
                }
                if (totalClock%GOODSPOOLTIME !=0){
                    scheduledTaskListener.goodsPoolTime(Thread.currentThread()+"--->99："+totalClock);
                }else {
                    Thread.sleep(new Random().nextInt(1000));
                    scheduledTaskListener.goodsPoolTime(Thread.currentThread()+"--->进行货道查询 间隔时间已到："+totalClock);
                }
                totalClock += COMMONPOOLTIME;
                if (totalClock>=TOTALSIZE){
                    totalClock = defaultClock;
                }
            } catch (Exception e) {
                Log.i(getClass().getSimpleName(), "线程停止拦截，线程终止");
            }
        }
    };

}
