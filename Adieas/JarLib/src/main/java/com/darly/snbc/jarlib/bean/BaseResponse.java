package com.darly.snbc.jarlib.bean;

/**
 * 包名称：com.darly.snbc.jarlib.bean
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/6 10:14
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class BaseResponse {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
