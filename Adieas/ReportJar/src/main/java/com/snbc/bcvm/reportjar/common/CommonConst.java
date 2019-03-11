package com.snbc.bcvm.reportjar.common;/**
 * Created by wangfeng1 on 2018/10/17.
 */

/**
 * 作者：王峰
 * 日期：2018/10/17
 */
public class CommonConst {
    public static int boxid = 0x01;//副柜id，无法传入boxid时使用

    public static int depot = 0x00;//仓号

    public static int ARRAY_LENGTH = 256;//数组长度

    public static int DEV_ARRAY_LENGTH = 2;//设备数组长度

    public static int ERROR_SUCCESS = 99; //成功

    public static int ERROR_PARAM = -1004; //参数错误

    public static int ERROR_PORT_OVERTIME = -1003;//端口超时错误
    public static int ERROR_INIT_FAIL = -1102; //货道自动识别失败

    public static int ERROR_OPENCOM = -1000; //函数失败

    public static int ERROR_PARSE_JSON = -1005; //json解析失败

    public static int ERROR_OPEN_FILE = -1401; //打开固件升级文件失败

    /*
     * 连接检测
     */
    public static void connectCheck(int boxid) {

    }

}
