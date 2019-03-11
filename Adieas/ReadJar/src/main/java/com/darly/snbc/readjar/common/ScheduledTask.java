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

    private static ScheduledTask instance;

    private ScheduledTask() {
    }

    public static ScheduledTask getInstance() {
        if (instance == null){
            synchronized (ScheduledTask.class){
                if (instance == null){
                    instance = new ScheduledTask();
                }
            }
        }
        return instance;
    }

    private ScheduledExecutorService service;

    private ScheduledTaskListener scheduledTaskListener;

    private boolean isScheduledRunning = false;//状态机是否运行
    private boolean isScheduledStop = false;//状态机是否暂停。


    private static int COMMONPOOLTIME = 1;//轮询时间间隔,1秒

    private static int DOORPOOLTIME = 3;//柜门闸门查询间隔时间，3秒
    private static int WORKPOOLTIME = 3;//整机状态查询间隔时间，3秒
    private static int HEARTPOOLTIME = 60;//心跳查询间隔时间，60秒
    private static int ERRORPOOLTIME = 3;//故障查询间隔时间，3秒
    private static int GOODSPOOLTIME = 600;//货道查询间隔时间，10分钟

    private long doorTime = 0;//柜门闸门查询计时器
    private long workTime = 0;//整机状态查询计时器
    private long heartTime = 0;//心跳查询计时器
    private long errorTime = 0;//故障查询计时器
    private long goodsTime = 0;//货道查询计时器

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
            service.scheduleWithFixedDelay(runnable, 0, COMMONPOOLTIME, TimeUnit.SECONDS);
            isScheduledRunning = true;
            isScheduledStop = false;
        } else {
            Log.i(getClass().getSimpleName(), "任务正在执行，不需要重新启动");
        }
    }

    public void shutDown() {
        if (service != null && !service.isShutdown()) {
            isScheduledStop = true;
            isScheduledRunning = false;
            scheduledTaskListener.onStopScheduledTask("状态机关闭，线程池关闭。释放资源");
            try {
                service.shutdown();
                if (!service.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                    service.shutdownNow();
                }
            } catch (InterruptedException e) {
                service.shutdownNow();
            }

        }
    }


    public void onPauseTask() {
        isScheduledStop = true;
        scheduledTaskListener.onPauseScheduledTask("轮询任务暂停");
    }

    public void onReStartTask() {
        isScheduledStop = false;
        scheduledTaskListener.onReStartScheduledTask("轮询任务重新开始");
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (isScheduledStop) {
                    scheduledTaskListener.onPauseScheduledTask("状态机暂停");
                    return;
                }
                for (int index = 0; index < 1; index++) {
                    if (System.currentTimeMillis() - doorTime >= DOORPOOLTIME * 1000) {
                        Thread.sleep(new Random().nextInt(1000));
                        doorTime = System.currentTimeMillis();
                        scheduledTaskListener.doorPoolTime(index, 0, 0, Thread.currentThread().toString(), "进行柜门闸门查询 间隔时间已到", (int) (doorTime / 1000));
                    }
                    if (isScheduledStop) {
                        scheduledTaskListener.onPauseScheduledTask("柜门闸门查询执行完成后。状态机暂停");
                        return;
                    }
                    if (System.currentTimeMillis() - workTime >= WORKPOOLTIME * 1000) {
                        Thread.sleep(new Random().nextInt(1000));
                        workTime = System.currentTimeMillis();
                        scheduledTaskListener.workPoolTime(index, 0, Thread.currentThread().toString(), "进行整机状态查询 间隔时间已到", (int) (workTime / 1000));
                    }
                    if (isScheduledStop) {
                        scheduledTaskListener.onPauseScheduledTask("整机状态查询执行完成后。状态机暂停");
                        return;
                    }
                    if (System.currentTimeMillis() - heartTime >= HEARTPOOLTIME * 1000) {
                        Thread.sleep(new Random().nextInt(1000));
                        heartTime = System.currentTimeMillis();
                        scheduledTaskListener.heartPoolTime(index, 0, 0, Thread.currentThread().toString(), "进行心跳查询 间隔时间已到", (int) (heartTime / 1000));
                    }
                    if (isScheduledStop) {
                        scheduledTaskListener.onPauseScheduledTask("心跳查询执行完成后。状态机暂停");
                        return;
                    }
                    if (System.currentTimeMillis() - heartTime >= ERRORPOOLTIME * 1000) {
                        Thread.sleep(new Random().nextInt(1000));
                        byte[] faultReportTmp = new byte[1];
                        faultReportTmp[0] = 0;
                        errorTime = System.currentTimeMillis();
                        scheduledTaskListener.errorPoolTime(index, null, faultReportTmp, Thread.currentThread().toString(), "进行故障查询 间隔时间已到", (int) (errorTime / 1000));
                    }
                    if (isScheduledStop) {
                        scheduledTaskListener.onPauseScheduledTask("故障查询行完成后。状态机暂停");
                        return;
                    }
                    if (System.currentTimeMillis() - goodsTime >= GOODSPOOLTIME * 1000) {
                        Thread.sleep(new Random().nextInt(1000));
                        goodsTime = System.currentTimeMillis();
                        scheduledTaskListener.goodsPoolTime(index, null, Thread.currentThread().toString(), "进行货道查询 间隔时间已到", (int) (goodsTime / 1000));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(getClass().getSimpleName(), "线程停止拦截，线程终止");
            }
        }
    };
}
