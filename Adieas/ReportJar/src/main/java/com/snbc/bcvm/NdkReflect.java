package com.snbc.bcvm;


import android.util.Log;

/**
 * C层通过JNI技术进行反射回调，满足回调机制
 * 包名称：com.darly.snbc.adieas.common
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/8 9:00
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class NdkReflect {
    private NdkReflect() {
    }
    private static class NdkReflectHodler {
        static NdkReflect instance = new NdkReflect();
    }
    public static NdkReflect getInstance() {
        return NdkReflectHodler.instance;
    }


    private NdkReflectListener ndkReflectListener;

    public void setNdkReflectListener(NdkReflectListener ndkReflectListener) {
        this.ndkReflectListener = ndkReflectListener;
    }

    /**c层回调上来的方法，过程一回调
     * @param key 识别信息
     * @param code 返回编码信息
     */
    public void onStepOneCallBack(String key,int code) {
        if (ndkReflectListener == null){
            Log.i("onStepOneCallBack","[接口未初始化...]");
        }else {
            ndkReflectListener.onStepOneCallBack(key,code);
        }
    }
    /**c层回调上来的方法，过程二回调
     * @param key 识别信息
     * @param code 返回编码信息
     */
    public void onStepTwoCallBack(String key,int code) {
        if (ndkReflectListener == null){
            Log.i("onStepTwoCallBack","[接口未初始化...]");
        }else {
            ndkReflectListener.onStepTwoCallBack(key,code);
        }
    }
    /**c层回调上来的方法，过程三回调
     * @param key 识别信息
     * @param code 返回编码信息
     */
    public void onStepThreeCallBack(String key,int code) {
        if (ndkReflectListener == null){
            Log.i("onStepThreeCallBack","[接口未初始化...]");
        }else {
            ndkReflectListener.onStepThreeCallBack(key,code);
        }
    }
    /**c层回调上来的方法，过程四回调
     * @param key 识别信息
     * @param code 返回编码信息
     */
    public void onStepFourCallBack(String key,int code)  {
        if (ndkReflectListener == null){
            Log.i("onStepFourCallBack","[接口未初始化...]");
        }else {
            ndkReflectListener.onStepFourCallBack(key,code);
        }
    }
}
