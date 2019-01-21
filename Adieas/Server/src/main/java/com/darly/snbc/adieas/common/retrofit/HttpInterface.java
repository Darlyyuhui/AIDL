package com.darly.snbc.adieas.common.retrofit;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/** 请求接口
 * @author Darly/张宇辉/2017/12/4 9:04
 * @version 1.0/com.darly.dubbo.retrofit
 */
public interface HttpInterface  {
//    @GET("/")
//    Observable<JsonObject> getNews(@Query("appid") String appid, @Query("date") String date);
//    @POST("mobile/login")
//    Observable<JsonObject> postlogin(@Body RequestBody route);


    @GET("/BLOK-NN114FSOO0507002053")
    Observable<JsonObject> getSign();



}
