package com.snbc.bcvm.reportjar.common.AppDatadef;

import java.util.List;

/**
 * 定义出货报告数据结构
 * auth by zhuruibing
 */
public class OutSaleReport {
    //副柜号
    byte boxid;
    byte depot;
    //订单号
    String orderNumber;
    //交易金额
    String tradeMoney;
    //出货时间
    String saleTime;
    //出货总结果
    byte totalResult;
    //每个货物的出货结果
    List<PerSaleResult> arrSaleResult;

    public byte getBoxid(){
        return boxid;
    }

    public void setBoxid(byte boxid){
        this.boxid=boxid;
    }

    public byte getDepot(){
        return depot;
    }

    public void setDepot(byte depot){
        this.depot=depot;
    }

    public String getOrderNumber(){
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber){
        this.orderNumber=orderNumber;
    }

    public String getTradeMoney(){
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney){
        this.tradeMoney=tradeMoney;
    }

    public String getSaleTime(){
        return saleTime;
    }

    public void setSaleTime(String SaleTime){
        this.saleTime=SaleTime;
    }

    public byte getTotalResult(){
        return totalResult;
    }

    public void setTotalResult(byte totalResult){
        this.totalResult=totalResult;
    }

    public List<PerSaleResult> getArrSaleResult(){
        return arrSaleResult;
    }

    public void setArrSaleResult(List<PerSaleResult> arrSaleResult){
        this.arrSaleResult=arrSaleResult;
    }

}
