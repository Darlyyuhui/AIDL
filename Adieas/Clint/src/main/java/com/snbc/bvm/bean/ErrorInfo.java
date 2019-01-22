package com.snbc.bvm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 错误对象实体类
 * 包名称：com.darly.snbc.adieas.bean
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/17 16:30
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ErrorInfo implements Parcelable {
    /**
     * 故障编码
     */
    private int code;
    /**
     * 故障描述
     */
    private String display;
    /**
     * 诱发原因
     */
    private String reason;
    /**
     * 解决方案
     */
    private String solution;
    /**
     * 所属系统
     */
    private String system;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//把javanbean中的数据写到Parcel
        dest.writeInt(code);
        dest.writeString(display);
        dest.writeString(reason);
        dest.writeString(solution);
        dest.writeString(system);
    }

    //添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口
    public static final Creator<ErrorInfo> CREATOR = new Creator<ErrorInfo>() {
        @Override
        public ErrorInfo createFromParcel(Parcel source) {//从Parcel中读取数据，返回person对象
            ErrorInfo info = new ErrorInfo();

            info.code = source.readInt();
            info.display = source.readString();
            info.reason = source.readString();
            info.solution = source.readString();
            info.system = source.readString();
            return info;
        }

        @Override
        public ErrorInfo[] newArray(int size) {
            return new ErrorInfo[size];
        }
    };
}
