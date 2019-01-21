package com.darly.snbc.adieas.base;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.darly.common.Common;
import com.darly.snbc.adieas.BuildConfig;
import com.darly.snbc.adieas.common.retrofit.RetrofitCfg;
import com.darly.snbc.adieas.ui.server.ServerService;

/**
 * 基础Application类
 * 包名称：com.darly.snbc.adieas.base
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/16 15:38
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        startService(new Intent(this,ServerService.class));

        Common.init().init(this, "Server");//初始化工具类中的缓存集合
        Common.init().initDlog(BuildConfig.DEBUG, "Server");//初始化工具类中的日志
        Common.init().initRetrofit(BaseApplication.getInstance().getVersionCode(), "https://boss.takeware.cn/lms/boss/drive/signInfo/", new RetrofitCfg());//初始化工具类中的网络请求模块

    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public static String getMessage(int resid){
        return instance.getResources().getString(resid);
    }


    /*
        获取项目的版本号
     */
    public int getVersionCode() {
        int version = 0;
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            version = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

}
