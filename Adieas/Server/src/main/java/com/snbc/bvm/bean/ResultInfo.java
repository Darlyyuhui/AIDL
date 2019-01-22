package com.snbc.bvm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * 错误列表或者输出列表
 * 包名称：com.darly.snbc.adieas.bean
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/11 12:54
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ResultInfo implements Parcelable {
    /**
     * 以String集合的形式返回详细信息，每条String对应一条信息
     */
    private ArrayList<String> result;

    private ArrayList<ErrorInfo> error;

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(ArrayList<String> result) {
        this.result = result;
    }

    public ArrayList<ErrorInfo> getError() {
        return error;
    }

    public void setError(ArrayList<ErrorInfo> error) {
        this.error = error;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//把javanbean中的数据写到Parcel
        dest.writeList(result);
        dest.writeList(error);
    }

    //添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口
    public static final Creator<ResultInfo> CREATOR = new Creator<ResultInfo>() {
        @Override
        public ResultInfo createFromParcel(Parcel source) {//从Parcel中读取数据，返回person对象
            ResultInfo info = new ResultInfo();
            info.result = new ArrayList<String>();
            source.readList(info.result, getClass().getClassLoader());

            info.error = new ArrayList<ErrorInfo>();
            source.readList(info.error, getClass().getClassLoader());
            return info;
        }

        @Override
        public ResultInfo[] newArray(int size) {
            return new ResultInfo[size];
        }
    };

    public String toJson() {
        return JSON.toJSONString(this);
    }


}
