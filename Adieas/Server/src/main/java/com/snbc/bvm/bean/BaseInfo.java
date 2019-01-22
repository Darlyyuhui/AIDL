package com.snbc.bvm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

/**
 * 输出参数对象
 * 包名称：com.darly.snbc.adieas.bean
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/11 12:54
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class BaseInfo implements Parcelable {

    private int code;
    private String msg;
    private int random;
    private InterfaceVersion head;
    private String body;//加密的对象体

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

    public InterfaceVersion getHead() {
        return head;
    }

    public void setHead(InterfaceVersion head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//把javanbean中的数据写到Parcel
        dest.writeInt(code);
        dest.writeString(msg);
        dest.writeInt(random);
        dest.writeParcelable(head, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeString(body);
    }

    //添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口
    public static final Creator<BaseInfo> CREATOR = new Creator<BaseInfo>() {
        @Override
        public BaseInfo createFromParcel(Parcel source) {//从Parcel中读取数据，返回person对象
            BaseInfo info = new BaseInfo();
            info.code = source.readInt();
            info.msg = source.readString();
            info.random = source.readInt();
            info.head = source.readParcelable(getClass().getClassLoader());
            info.body = source.readString();
            return info;
        }

        @Override
        public BaseInfo[] newArray(int size) {
            return new BaseInfo[size];
        }
    };


    public ResultInfo toResultInfo(String json) {
        return JSON.parseObject(json, ResultInfo.class);
    }
}
