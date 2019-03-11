package com.snbc.bcvm.reportjar.base;

import android.content.Context;
import android.view.WindowManager;

/**
 * 對封裝后的工具使用Image進行初始化
 *
 * @author Darly/张宇辉/2017/11/23 14:44
 * @version 1.0/com.darly.dview.observer
 * Copyright (c) 2017 Organization D.L. zhangyuhui All rights reserved.
 */

public class ReportJarInit implements ObserverListener {

    private ReportJarInit() {
    }

    private static class ReportJarInitHodler {
       private static ReportJarInit instance = new ReportJarInit();
    }
    public static ReportJarInit getInstance() {
        return ReportJarInitHodler.instance;
    }



    @Override
    public void initConfig(boolean isDebug, Context context) {
        ReportJarCfg.init(context);
        calculate(context);
        ReportJarCfg.setIsDebug(isDebug);
        ReportJarCfg.setInit(true);
    }

    /**
     * 计算屏幕宽高以及后续辅助参数。都可以在这里进行完善
     *
     * @param context 引用类
     */
    private void calculate(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        ReportJarCfg.setWidth(wm.getDefaultDisplay().getWidth());
        ReportJarCfg.setHeight(wm.getDefaultDisplay().getHeight());
    }
}
