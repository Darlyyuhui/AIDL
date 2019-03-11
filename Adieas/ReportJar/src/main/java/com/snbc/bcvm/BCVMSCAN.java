package com.snbc.bcvm;

public class BCVMSCAN {
	// 打开端口
	public static native int Int_OpenScanComm(int nType);

	// 关闭端口
	public static native int Int_CloseScanComm();

	// 开始扫描
	public static native int Int_StartScan(byte[] outInfo);
	
	// 结束扫描
	public static native int Int_EndScan();

	static {
		System.loadLibrary("BCVMSDK_SCAN");
	}
}
