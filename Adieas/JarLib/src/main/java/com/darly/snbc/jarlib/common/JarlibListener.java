package com.darly.snbc.jarlib.common;

/**
 * 异步回调接口
 * 包名称：com.darly.snbc.jarlib.common
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/6 9:16
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface JarlibListener<T> {

    void onStart();

    void onSuccess(T t);

    void onFailed(T t);
}
