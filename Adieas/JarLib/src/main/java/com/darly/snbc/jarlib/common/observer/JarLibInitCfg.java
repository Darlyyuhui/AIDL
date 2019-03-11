package com.darly.snbc.jarlib.common.observer;

import android.content.Context;
import android.view.WindowManager;

/**
 * 對封裝后的工具使用Image進行初始化
 *
 * @author Darly/张宇辉/2017/11/23 14:44
 * @version 1.0/com.darly.dview.observer
 * Copyright (c) 2017 Organization D.L. zhangyuhui All rights reserved.
 */

public class JarLibInitCfg implements ObserverListener {

    private static JarLibInitCfg instance = null;

    private JarLibInitCfg() {
    }

    public static JarLibInitCfg getInstance() {
        if (instance == null) {
            instance = new JarLibInitCfg();
        }
        return instance;
    }

    @Override
    public void initConfig(boolean isDebug, Context context) {
        Cfg.init(context);
        calculate(context);
        Cfg.setIsDebug(isDebug);
        Cfg.setInit(true);
    }

    /**
     * 计算屏幕宽高以及后续辅助参数。都可以在这里进行完善
     *
     * @param context 引用类
     */
    private void calculate(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Cfg.setWidth(wm.getDefaultDisplay().getWidth());
        Cfg.setHeight(wm.getDefaultDisplay().getHeight());
    }
}
