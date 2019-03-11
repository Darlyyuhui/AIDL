package com.snbc.bvm.bean;


import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * 输入参数对象详细参数信息
 * 包名称：com.darly.snbc.adieas.bean
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/18 14:17
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ParamerInfo{
    private int boxid;
    private int jboxid;
    private int workmode;
    private int timeout;
    private int xyselect;
    private int xselect;
    private int yselect;
    private int count;
    private int startposition;              //int 起始位置标识,暂只为0
    private int intentposition;                 //int 目的位置标识,0：归位位置（输入归位位置会回到原点,输入此参数时,层列数输入任意值）；1：货道位置（输入货道位置会到达指定位置）
    private int positionX;              //int 货物所在层（1-10）
    private int positionY;              //int  货物所在列
    private int elcspeed;           //int xy轴电机速度,暂为1
    private int chspeed;                //int 取货斗电机速度 ,暂为1
    private int laser;              // int 是否激光测距 1：是 0：否 //int
    private int pickup;             //int 是否取货：1：是 0：否
    private int price;                  //int 单位：分
    private int goodsnum;               // int 暂时为1
    private int state;              //1：进入维护模式 0：退出维护模式
    private int type;                   //0：顶部灯 1：展仓1 2：展仓 3：取货灯，格子柜参数：00为照明灯
    private int addr;               //0：中心控制板 1：X轴板 2：Y轴板 3：取货斗板 16：电源板
    private int size;               //读取日志大小，单位：K
    private int nLay;                   //层数不能为0
    private int nRow;               //列数不能为0
    private int querytype;              //宽高 0：货道高度 ，1：货道宽度
    private int mode;                   //类型 0:常温模式 1：制冷模式 2：制热模式 3：智能模式
    private int onTemp;                 //制冷ON温度范围4-25度on>off
    private int offTemp;                //制冷OFF温度范围4-25度on>off
    private String goodsNumThick;
    private String key;//秘钥 16字节长度字符串，密钥 当type取值0或1时 密钥可以任意设置
    private String ordernumber;//String 订单号，最大32字节
    private String code;//最大64字节长度字符串
    private String filepath;//升级文件全路径，不允许有中文
    //签到接口进行修改，传递vemId和assetCode参数
    private String vemId;   //售货机编码
    private Map<String,String> assetCode;    //参数是Map<String,String>类型，代表意思资产编码组（key是出厂编码，value是对应的资产编码）

    private int depot;

    public int getBoxid() {
        return boxid;
    }

    public void setBoxid(int boxid) {
        this.boxid = boxid;
    }

    public int getJboxid() {
        return jboxid;
    }

    public void setJboxid(int jboxid) {
        this.jboxid = jboxid;
    }

    public int getWorkmode() {
        return workmode;
    }

    public void setWorkmode(int workmode) {
        this.workmode = workmode;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getXyselect() {
        return xyselect;
    }

    public void setXyselect(int xyselect) {
        this.xyselect = xyselect;
    }

    public int getXselect() {
        return xselect;
    }

    public void setXselect(int xselect) {
        this.xselect = xselect;
    }

    public int getYselect() {
        return yselect;
    }

    public void setYselect(int yselect) {
        this.yselect = yselect;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStartposition() {
        return startposition;
    }

    public void setStartposition(int startposition) {
        this.startposition = startposition;
    }

    public int getIntentposition() {
        return intentposition;
    }

    public void setIntentposition(int intentposition) {
        this.intentposition = intentposition;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getElcspeed() {
        return elcspeed;
    }

    public void setElcspeed(int elcspeed) {
        this.elcspeed = elcspeed;
    }

    public int getChspeed() {
        return chspeed;
    }

    public void setChspeed(int chspeed) {
        this.chspeed = chspeed;
    }

    public int getLaser() {
        return laser;
    }

    public void setLaser(int laser) {
        this.laser = laser;
    }

    public int getPickup() {
        return pickup;
    }

    public void setPickup(int pickup) {
        this.pickup = pickup;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGoodsnum() {
        return goodsnum;
    }

    public void setGoodsnum(int goodsnum) {
        this.goodsnum = goodsnum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getnLay() {
        return nLay;
    }

    public void setnLay(int nLay) {
        this.nLay = nLay;
    }

    public int getnRow() {
        return nRow;
    }

    public void setnRow(int nRow) {
        this.nRow = nRow;
    }

    public int getQuerytype() {
        return querytype;
    }

    public void setQuerytype(int querytype) {
        this.querytype = querytype;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
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

    public String getGoodsNumThick() {
        return goodsNumThick;
    }

    public void setGoodsNumThick(String goodsNumThick) {
        this.goodsNumThick = goodsNumThick;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getVemId() {
        return vemId;
    }

    public void setVemId(String vemId) {
        this.vemId = vemId;
    }

    public Map<String, String> getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(Map<String, String> assetCode) {
        this.assetCode = assetCode;
    }

    public int getDepot() {
        return depot;
    }

    public void setDepot(int depot) {
        this.depot = depot;
    }

    public String toJson(){
        return JSON.toJSONString(this);
    }

}
