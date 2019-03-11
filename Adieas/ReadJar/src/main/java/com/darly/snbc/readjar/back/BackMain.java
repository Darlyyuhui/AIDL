package com.darly.snbc.readjar.back;

import android.util.Log;

/**
 * 测试调用端
 * 包名称：com.darly.snbc.readjar.back
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/4 10:24
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class BackMain {

    public static void main(String [] arg){
        //异步示例
        AsynBack asyn = new AsynBack();
        asyn.setOnBackListener(new OnBackListener() {
            @Override
            public void onStart() {
                //异步开始
            }

            @Override
            public void onSuccess(String result) {
                //异步成功返回
                System.out.println(result);
            }

            @Override
            public void onFasle(String result) {
                //异步失败返回
                System.out.println(result);
            }
        });
        String key = asyn.AsynTask();
        System.out.printf(key+"---");

        //同步示例
        SyncBack sync = new SyncBack();
        sync.setOnBackListener(new OnBackListener() {
            @Override
            public void onStart() {
                //同步开始
            }

            @Override
            public void onSuccess(String result) {
                //同步成功返回
            }

            @Override
            public void onFasle(String result) {
                //同步失败返回
            }
        });
       String entity =  sync.SyncTask();
        //同步结果entity
    }



}
