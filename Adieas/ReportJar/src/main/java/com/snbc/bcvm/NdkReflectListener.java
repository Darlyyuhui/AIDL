package com.snbc.bcvm;

/**
 * Jar包中的内部回调接口。异步调用接口预留方案。
 * 里面的方法需要和NdkReflect中的反射回调方法保持一致。
 * 包名称：com.snbc.bcvm
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/12 10:59
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface NdkReflectListener {
    void onStepOneCallBack(String key,int code);
    void onStepTwoCallBack(String key,int code);
    void onStepThreeCallBack(String key,int code);
    void onStepFourCallBack(String key,int code);
}
