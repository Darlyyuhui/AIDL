package com.snbc.bvm.bean;

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

    public String getVersion() {
        return version;
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
        GETFACTORYCODE("GetFactoryCode"),//售卖端获取出厂编码接口，中间层通过文件进行读取。
        BVMINIT("BVMInit"),//中间层初始化接口，根据现在要求传递参数。
        BVMINITXYROAD("BVMInitXYRoad"),//进行货道扫描，当设备重新上电或层数、货道变动时调用，根据现在要求传递参数。
        BVMQUERYINITRESULT ("BVMQueryInitResult"),//查询货道扫描结果，根据现在要求传递参数。
        BVMMOVESALEGOODSPRO("BVMMoveSaleGoodsPro"),//备货流程，可移动到备货位置进行激光测距，图像识别等操作，也可取货,可用于盘点，根据现在要求传递参数。
        BVMCTRLSALEGOODSSTEPPRO("BVMCtrlSaleGoodsStepPro"),//备货后的出货，备货后如果想直接出货，可调用此接口出货，根据现在要求传递参数。
        BVMSTARTSHIP("BVMStartShip"),//新出货接口，控制售货机出货，根据现在要求传递参数。
        BVMELECDOORCTRL("BVMElecDoorCtrl"),//开主副柜电控锁，根据现在要求传递参数。
        BVMQUERYBOXINFO("BVMQueryBoxInfo"),//查询固件版本号，厂商信息，根据现在要求传递参数。
        BVMSETCOLDHEATMODEL("BVMSetColdHeatModel"),//制冷制热模式设置，根据现在要求传递参数。
        BVMGETCOLDHEATMODEL("BVMGetColdHeatModel"),//制冷制热模式查询，根据现在要求传递参数。
        BVMSETCOLDMODEL("BVMSetColdModel"),//制冷模式设置，根据现在要求传递参数。
        BVMGETCOLDMODE("BVMGetColdMode"),//制冷模式查询，根据现在要求传递参数。
        BVMSETCOLDTEMP("BVMSetColdTemp"),//制冷温度设置，根据现在要求传递参数。
        BVMGETCOLDTEMP("BVMGetColdTemp"),//制冷温度查询，根据现在要求传递参数。
        BVMSETHEATTEMP("BVMSetHeatTemp"),//制热温度设置，根据现在要求传递参数。
        BVMGETHEATTEMP("BVMGetHeatTemp"),//制热温度查询，根据现在要求传递参数。
        BVMGETCOLDHEATTEMP("BVMGetColdHeatTemp");//当前温度查询，根据现在要求传递参数。

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


    public ParamerInfo toParamerInfo(String json) throws JSONException {
        return JSON.parseObject(json, ParamerInfo.class);
    }

}
