package com.snbc.bcvm.reportjar.common;

import android.content.Context;

public interface MySerialPortInterface {
	
	/*
	 * OpenSerialPort 打开串口
	 * 0 成功 其他 失败
	 */
	public int OpenSerialPort() ;
	
	/*
	 * CloseSerialPort 关闭串口
	 * 0 成功 其他 失败
	 */
	public int CloseSerialPort() ;
	
	/*
	 * SendCmd 发送数据(发送之前清空原有缓冲区的数据)
	 * szCmd 发送数据缓冲区 发送的实际长度是 szCmd.length
	 * true 成功 false 失败
	 */
	public boolean SendCmd(byte[] szCmd) ;
	
	/*
	 * SendCmdNotClear 发送数据(发送之前不清空原有缓冲区的数据)
	 * szCmd 发送数据缓冲区 发送的实际长度是 szCmd.length
	 * true 成功 false 失败
	 */
	public boolean SendCmdNotClear(byte[] szCmd) ;
	
	/*
	 * RecvCmd 接收串口数据
	 * szCmd 接收到的数据缓冲区
	 * 返回实际接收的长度
	 */
	public int RecvCmd(byte[] szCmd) ;
	
	/*
	 * SetParam 设置串口参数
	 * String strDevPath 串口路径名 (/dev/ttyUSB0)
	 * int iParam 波特率
	 */
	public int SetParam(String strDevPath, int iParam) ;
	
	/*
	 * SetParam 设置 Context 参数
	 */
	public int SetParam(Context mContext) ;
}
