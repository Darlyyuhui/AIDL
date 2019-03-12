package com.snbc.bcvm.reportjar.entity;

/**
 * 温度数据类。
 * 包名称：com.snbc.bcvm.reportjar.entity
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/12 9:33
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class TempEntity {
    private int boxTemp=-1;  //箱体温度
    private int outsideTemp=-1; //外界环境温度
    private int onTemp=-1;  //制冷ON温度，制热ON温度
    private int offTemp=-1; //制冷OFF温度，制热OFF温度

    public int getBoxTemp() {
        return boxTemp;
    }

    public void setBoxTemp(int boxTemp) {
        this.boxTemp = boxTemp;
    }

    public int getOutsideTemp() {
        return outsideTemp;
    }

    public void setOutsideTemp(int outsideTemp) {
        this.outsideTemp = outsideTemp;
    }

    public int getOnTemp() {
        return onTemp;
    }

    public void setOnTemp(int onTemp) {
        this.onTemp = onTemp;
    }

    public int getOffTemp() {
        return offTemp;
    }

    public void setOffTemp(int offTemp) {
        this.offTemp = offTemp;
    }
}
