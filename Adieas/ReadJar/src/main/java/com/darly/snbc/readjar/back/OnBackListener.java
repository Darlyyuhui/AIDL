package com.darly.snbc.readjar.back;

/**
 * 接口定义
 * 包名称：com.darly.snbc.readjar.back
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/4 10:23
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface OnBackListener {

    void onStart();

    void onSuccess(String result);

    void onFasle(String result);
}
