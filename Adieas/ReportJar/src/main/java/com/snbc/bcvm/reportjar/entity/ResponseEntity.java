package com.snbc.bcvm.reportjar.entity;

/**
 * 所有中间层bcvmcommon模块 函数的返回对象
 * 注意：对ResponseEntity<Integer> 函数无特殊说明时表示: 成功时返回 99,失败返回-1000
 * 包名称：com.snbc.bcvm.bcvmcommon.entity
 * 作者：qinhaonan 项目名称：Vem
 * 日期：2019/1/18
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：qinhaonan@newbeiyang.com
 */
public class ResponseEntity<T> {


    /**
     * code : 99
     * errorDetails : {"head":{"version":"1.0","id":"SNBC"},"body":{"error":[{"code":1,"system":"XX","display":"XX","reason":"XX","solution":"XX"},{"code":2,"system":"XX","display":"XX","reson":"XX","solution":"XX"}]}}
     */

    private int code=-1;
    private String message="";
    private ErrorEntity errorEntity;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorEntity getErrorEntity() {
        return errorEntity;
    }

    public void setErrorEntity(ErrorEntity errorEntity) {
        this.errorEntity = errorEntity;
    }
}
