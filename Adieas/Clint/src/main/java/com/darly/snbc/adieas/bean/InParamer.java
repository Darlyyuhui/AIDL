package com.darly.snbc.adieas.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * 参数输入字段加密对象
 * 包名称：com.darly.snbc.adieas.bean
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/11 15:11
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class InParamer implements Parcelable {
    private String id = "SNBC";
    private String version = "V1.0";
    private String paramer;//调用接口传递的参数加密的字符串
    private String random ;//产生加密过程的随机数
    private MethodEnum method; //调用的接口名称


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

    public String getParamer() {
        return paramer;
    }

    public void setParamer(String paramer) {
        this.paramer = paramer;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public MethodEnum getMethod() {
        return method;
    }

    public void setMethod(MethodEnum method) {
        this.method = method;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//把javanbean中的数据写到Parcel
        dest.writeString(id);
        dest.writeString(version);
        dest.writeString(paramer);
        dest.writeString(random);
        dest.writeInt(method.ordinal());
    }

    //添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口
    public static final Creator<InParamer> CREATOR = new Creator<InParamer>() {
        @Override
        public InParamer createFromParcel(Parcel source) {//从Parcel中读取数据，返回person对象
            InParamer info = new InParamer();
            info.id = source.readString();
            info.version = source.readString();
            info.paramer = source.readString();
            info.random = source.readString();
            info.method = MethodEnum.values()[source.readInt()];
            return info;
        }

        @Override
        public InParamer[] newArray(int size) {
            return new InParamer[size];
        }
    };

    public enum MethodEnum {
        BVMINIT("BVMInit"),//中间层初始化接口
        BVMOPENPORT("BVMOpenPort"),//打开串口接口
        BVMCLOSEPORT("BVMClosePort"),//关闭串口接口
        BVMGETRUNNINGSTATE("BVMGetRunningState"),//获取整机状态接口
        BVMGETDOORSTATE("BVMGetDoorState");//获取门禁状态

        MethodEnum(String desc) {
            this.desc = desc;
        }

        private String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    public ParamerInfo toParamerInfo(String json) throws JSONException{
        return JSON.parseObject(json, ParamerInfo.class);
    }

}
