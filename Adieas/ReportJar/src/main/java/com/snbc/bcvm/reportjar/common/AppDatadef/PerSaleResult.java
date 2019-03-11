package com.snbc.bcvm.reportjar.common.AppDatadef;

/**
 * 定义每个货物的出货结果
 */
public class PerSaleResult{
    private byte perResult; //出货结果
    private int positionX; //层数
    private int positionY; //列数
    private int laserResult; //激光测距

    public byte getPerResult(){
        return perResult;
    }

    public void setPerResult(byte perResult){
        this.perResult=perResult;
    }

    public int getPositionX(){
        return positionX;
    }

    public void setPostionX(int position){
        this.positionX=position;
    }
    public int getPositionY(){
        return positionY;
    }

    public void setpositionY(int position){
        this.positionY=position;
    }
    public int getLaserResult(){
        return laserResult;
    }

    public void setLaserResult(int laserResult){
        this.laserResult=laserResult;
    }
}
