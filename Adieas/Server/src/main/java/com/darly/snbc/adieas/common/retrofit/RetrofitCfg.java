package com.darly.snbc.adieas.common.retrofit;

import com.darly.common.retrofit.reobs.RxobsListener;

import okhttp3.Request;

/** 根据需求进行请求头部传递参数
 * @author Darly/张宇辉/2017/12/4 13:39
 * @version 1.0/com.darly.dubbo.retrofit
 */
public class RetrofitCfg implements RxobsListener {
    @Override
    public Request.Builder initHeader(Request.Builder builder) {
        return builder;
    }

}
