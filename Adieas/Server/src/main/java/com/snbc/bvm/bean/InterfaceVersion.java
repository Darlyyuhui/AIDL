package com.snbc.bvm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 接口版本信息
 * 包名称：com.darly.snbc.adieas.bean
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/17 16:26
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class InterfaceVersion implements Parcelable {
    /**
     * 接口描述字段
     */
    private String id = "SNBC";
    /**
     * 接口版本号
     */
    private String version = "V1.0";


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//把javanbean中的数据写到Parcel
        dest.writeString(id);
        dest.writeString(version);
    }
    //添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口
    public static final Creator<InterfaceVersion> CREATOR = new Creator<InterfaceVersion>(){
        @Override
        public InterfaceVersion createFromParcel(Parcel source) {//从Parcel中读取数据，返回person对象
            InterfaceVersion info = new InterfaceVersion();
            info.id = source.readString();
            info.version = source.readString();
            return info;
        }
        @Override
        public InterfaceVersion[] newArray(int size) {
            return new InterfaceVersion[size];
        }
    };
}
