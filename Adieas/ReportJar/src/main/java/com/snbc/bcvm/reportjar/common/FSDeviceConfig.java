package com.snbc.bcvm.reportjar.common;

/**
 * 售货机配置文件字符串
 * Created by wangfeng on 2017/12/19.
 */

public class FSDeviceConfig {

    public static String FSDevConfig =

            "# config version\r\n"+
            "# 1 = OPENBOARD else = CLOSEBOARD\r\n"+
            "SENDBROAD = 1\r\n"+
            "# BOXNUMBER\r\n"+
            "BOXNUMBER = 1\r\n"+
            "# 0 = [mindeES4800AT] 1 = [MOTO] 2 = [DEWO]"+"\r\n"+
            "SCANDEV = 0\r\n"+
            "PORTNAME = /dev/ttyACM0\r\n"+
            "PORTNUM = 9600\r\n";

}
