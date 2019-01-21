package com.snbc.bcvm;

public class BCVMZK {

    /************************************************************************/
    /* 初始化设备类型 */

    /************************************************************************/
    // 初始化设备类型
    public static native int Int_IniDeviceType(int nType);

    // 获取SO库版本号
    public static native int Int_GetSoVersion(byte[] version);

    // 设置密钥
    public static native int Int_SetKey(int nType, byte[] uKey);

    /************************************************************************/
    /* 端口 */

    /************************************************************************/
    // 初始化串口
    public static native int Int_IniZKComm();

    // 关闭串口
    public static native int Int_CloseZKComm();

    /************************************************************************/
    /* 协议相关指令 */

    /************************************************************************/
    // 查询程序版本号
    public static native int Int_GetJGBasicMsg(int[] nFG, byte[] supBuf, byte[] verBuf);

    // 心跳使能
    public static native int Int_ZMHeartBeatState(int[] nFG, int nState);

    // 心跳指令 - android和主控板心跳
    public static native int Int_ZKHeartBeat(int[] nFG);

    /************************************************************************/
    /* 设备状态指令 */

    /************************************************************************/
    // 系统状态获取
    public static native int Int_GetFGWorkState(int[] nFG, int[] stateBuf);

    // 设备门状态获取
    public static native int Int_GetFGDoorState(int[] nFG, int[] nState);

    // 错误状态获取
    public static native int Int_GetFGFault(int[] nFG, byte[] uFaultClean, byte[] uFaultReport);

    // 错误状态确认
    public static native int Int_CleanSysFault(int[] nFG, byte[] uFaultClean, int nLen);

    /************************************************************************/
    /* 货道相关指令 */

    /************************************************************************/
    // 货道识别
    public static native int Int_IniXY(int[] nFG, int nLay);

    // 货道识别过程查询
    public static native int Int_GetXYActionState(int[] nFG, byte[] uState);

    // 货道自动识别流程
    public static native int Int_IniXYPro(int[] nFG, int nXY, int nIfWork, int nOutTime);

    // 层列数查询
    public static native int Int_GetFGLayRow(int[] nFG, int[] nLayRow);

    // 货道层列数清除
    public static native int Int_CleanFGLayRow(int[] nFG);

    // 货道禁用可用状态设定
    public static native int Int_CtrlFGLayRow(int[] nFG, int nLay, int nRow, int[] nState);

    // 货道禁用可用状态取得
    public static native int Int_GetFGLayRowState(int[] nFG, int nLay, int nRow, int[] nState);

    // 货道状态取得
    public static native int Int_GetGoodsState(int[] nFG, int nLay, int nRow, int[] nState);

    // 自动盘点
    public static native int Int_AutoCheckGoods(int[] nFG, int nLay, int nRow);

    // 自动盘点过程查询
    public static native int Int_AutoCheckGoodsAction(int[] nFG, byte[] uState);

    // 自动盘点数据查询
    public static native int Int_AutoCheckGoodsNum(int[] nFG, int nLay, int nRow, int[] nGoodsNum);

    // 自动盘点数量清除
    public static native int Int_AutoCheckGoodsClean(int[] nFG, int nLay, int nRow);

    // 货物厚度设置指令
    public static native int Int_AutoCheckGoodsThick(int[] nFG, int[] nGoodsNumThick,
                                                     int nGoodsLen);

    // 自动盘点流程
    public static native int Int_AutoCheckGoodsPro(int[] nFG, int[] nGoodsNumMsg, int[] nGoodsNum);

    // 货道距离查询
    public static native int Int_GetGoodsLineRange(int[] nFG, int[] nInfoParam, int[] nState);

    // 品质过期货道查询
    public static native int Int_GetOutQuality(int[] nFG, byte[] nState);

    // 保质期管理使能设置
    public static native int Int_SetQualityRun(int[] nFG, int[] nParam);

    // 保质期管理使能查询
    public static native int Int_GetQualityRun(int[] nFG, int[] nParam, int[] nState);

    // 保质期设置
    public static native int Int_SetQualityTime(int[] nFG, int[] nParam, int[] nTime);

    // 保质期查询
    public static native int Int_GetQualityTime(int[] nFG, int[] nParam, int[] nTime);

    // 暂停销售时间设置
    public static native int Int_SetPauseTime(int[] nFG, int[] nParam, int[] nTime);

    // 暂停销售时间查询
    public static native int Int_GetPauseTime(int[] nFG, int[] nParam, int[] nTime);

    // 超温管理使能设置
    public static native int Int_SetOutTempRun(int[] nFG, int[] nParam);

    // 超温管理使能查询
    public static native int Int_GetOutTempRun(int[] nFG, int[] nParam, int[] nState);

    // 超温管理设置
    public static native int Int_SetOutTempManager(int[] nFG, int[] nParam, int[] nTime);

    // 超温管理查询
    public static native int Int_GetOutTempManager(int[] nFG, int[] nParam, int[] nTime);

    // 超温持续时间查询
    public static native int Int_GetOutTempContinuity(int[] nFG, int[] nParam, int[] nTime);

    // 超温持续时间清除
    public static native int Int_CleanOutTemp(int[] nFG, int[] nParam);

    /************************************************************************/
    /* 售货相关指令 */

    /************************************************************************/
    // 出货指示
    public static native int Int_CtrlSaleGoods(int[] nFG, String orderNum, float fPrice,
                                               int[] nGoodsParam);

    // 出货报告查询
    public static native int Int_GetLastSaleReport(int[] nFG, String orderNum, byte[] reportBuf);

    // 再次开门指示
    public static native int Int_ReSaleGoods(int[] nFG);

    // 备餐（备货）
    public static native int Int_CtrlMoveSaleGoods(int[] nFG, int[] nGoodsParam);

    // 备餐（备货）结果查询
    public static native int Int_GetCtrlMoveSaleGoods(int[] nFG, byte[] uState);

    // 备餐（备货） - 流程接口
    public static native int Int_CtrlMoveSaleGoodsPro(int[] nFG, int[] nGoodsParam, byte[] uState);

    // 取餐（取货） - 流程接口 - 货道参数合并 - 分步流程
    public static native int Int_IniCtrlSaleGoodsStepPro(int[] nFG, String orderNum, float fPrice,
                                                         int[] nGoodsParam, byte[] reportBuf);

    // 取餐（取货） - 流程接口 - 完整流程
    public static native int Int_IniCtrlSaleGoodsPro(int[] nFG, String orderNum, float fPrice,
                                                     int[] nGoodsParam, byte[] reportBuf);

    /************************************************************************/
    /* 系统设置指令 */

    /************************************************************************/
    // 维护开始/结束
    public static native int Int_IfMaintenance(int[] nFG, int nState);

    // 通信许可/禁止
    public static native int Int_CommForbid(int[] nFG, int nState);

    // 通信波特率调整
    public static native int Int_AdjustBaudRate(int[] nFG, int nRate);

    // 资产编码设置
    public static native int Int_SetJGNum(int[] nFG, String devNum);

    // 资产编码查询
    public static native int Int_GetJGNum(int[] nFG, byte[] devNum);

    // 客户资产编码设置
    public static native int Int_SetGodJGNum(int[] nFG, String devNum);

    // 客户资产编码查询
    public static native int Int_GetGodJGNum(int[] nFG, byte[] devNum);

    // 加密密钥设置
    public static native int Int_SetSecortKey(int[] nFG, byte[] uKey);

    // 系统时间设定
    public static native int Int_SetSystime(int[] nFG, String sysTime);

    // 系统时间取得
    public static native int Int_GetSystime(int[] nFG, byte[] reSysTime);

    // 电源控制
    public static native int Int_CtrlSwitch(int[] nFG, int nType, int nState);

    // 插削使能设置
    public static native int Int_PlugState(int[] nFG, int nType, int nState);

    // 主柜门打开控制
    public static native int Int_ElecDoorCtrl(int[] nFG);

    // LED灯控制
    public static native int Int_CrtlLightState(int[] nFG, int nType, int nState);

    // 设备同步设置指令
    public static native int Int_SetDeviceSynchro(int[] nFG);

    // 设备同步获取指令
    public static native int Int_GetDeviceSynchro(int[] nFG, byte[] uState);

    // 获取设备配置
    public static native int Int_GetMachineConfig(int[] nFG, byte[] uState);

    // 制冷制热模式设置
    public static native int Int_SetColdHeatModel(int[] nFG, int nModel);

    // 制冷制热模式查询
    public static native int Int_GetColdHeatModel(int[] nFG, int[] nModel);

    // 制冷模式设置
    public static native int Int_SetColdModel(int[] nFG, int nModel);

    // 制冷模式查询
    public static native int Int_GetColdModel(int[] nFG, int[] nModel);

    // 制冷温度设置
    public static native int Int_SetColdTemp(int[] nFG, int[] nTemp);

    // 制冷温度查询
    public static native int Int_GetColdTemp(int[] nFG, int[] nTemp);

    // 制热温度设置
    public static native int Int_SetHeatTemp(int[] nFG, int[] nTemp);

    // 制热温度查询
    public static native int Int_GetHeatTemp(int[] nFG, int[] nTemp);

    // 当前温度查询
    public static native int Int_GetColdHeatTemp(int[] nFG, int[] nTemp);

    // 工作模式设置
    public static native int Int_SetECWork(int[] nFG, int[] nParam);

    // 工作模式查询
    public static native int Int_GetECWork(int[] nFG, int[] nParam);

    // 节能时间带设置
    public static native int Int_SetECTime(int[] nFG, int[] nParam);

    // 节能时间带查询
    public static native int Int_GetECTime(int[] nFG, int[] nParam);

    // 节能星期设置
    public static native int Int_SetECWeek(int[] nFG, int[] nParam);

    // 节能星期查询
    public static native int Int_GetECWeek(int[] nFG, int[] nParam);

    ////////////// 碳晶板加热相关指令
    // 制热开关设置
    public static native int Int_SetCarbonHeatSwitch(int[] nFG, int[] nParam);

    // 制热开关查询
    public static native int Int_GetCarbonHeatSwitch(int[] nFG, int[] nParam, int[] nState);

    // 制热温度参数设置
    public static native int Int_SetCarbonHeatTemp(int[] nFG, int[] nParam);

    // 制热温度参数查询
    public static native int Int_GetCarbonHeatTemp(int[] nFG, int[] nParam, int[] nState);

    // 当前温度获取
    public static native int Int_GetCarbonHeatSensorTemp(int[] nFG, int[] nTemp);

    /************************************************************************/
    /* 通用维护指令 */

    /************************************************************************/
    // 设备复位指令
    public static native int Int_RestartZXYQ(int[] nFG);

    // 通讯测试指令
    public static native int Int_ZMHeartBeat(int[] nFG, int nAddr);

    // EEPROM读指令
    public static native int Int_ReadEEPROM(int[] nFG, int nType, int nEEAddr, int nEELen,
                                            int[] stateBuf);

    // EEPROM写指令
    public static native int Int_WriteEEPROM(int[] nFG, int nType, int nEEAddr, int nEELen,
                                             int[] stateBuf);

    // 保存出厂设置指令
    public static native int Int_SaveFactorySetting(int[] nFG, int nAddr);

    // 恢复出厂设置指令
    public static native int Int_ResetFactorySetting(int[] nFG, int nAddr);

    // 历史记录数据查询
    public static native int Int_CheckHistoryNum(int[] nFG, int nAddr, byte[] uStateBuf);

    // 历史记录数据清除
    public static native int Int_CleanHistoryNum(int[] nFG, int nAddr, int nNum);

    // 系统复位状态查询
    public static native int Int_ResetSystemState(int[] nFG, int nAddr, byte[] nStateBuf);

    // 线程栈空间查询
    public static native int Int_ThreadStack(int[] nFG, int nAddr, byte[] nStateBuf);

    /************************************************************************/
    /* 升级指令 */

    /************************************************************************/
    // 更新柜机固件 - 流程接口
    public static native int Int_UpdateDevDriVer(int[] nFG, String path);

    /***********************************************************************
     * 以下功能内部测试使用，不提供给客户 - 提供前请删除本段及下面代码
     *********************************************************************/

    /************************************************************************/
    /* 明文特殊指令 */

    /************************************************************************/
    // 读取固件日志
    public static native int Int_ReadDriverLog(int[] nFG, int nSize);

    // 进入/退出运维模式指令
    public static native int Int_Maintain(int[] nFG, int nState);

    // 查询当前指令模式指令
    public static native int Int_GetModleState(int[] nFG, int[] nState);

    /************************************************************************/
    /* 设备维护指令 */

    /************************************************************************/
    ////////////// 子板透传指令
    /// 电机板透传指令
    // XY轴层列位置查询指令
    public static native int Int_GetXYCoordinate(int[] nFG, int nXY, int nLay, int[] nCoordinate);

    // XY轴补偿值查询指令
    public static native int Int_GetXYCompensate(int[] nFG, int nXY, int[] settingBuf);

    /// 取货斗透传指令
    // 传感器AD值查询指令
    public static native int Int_GetHDSensorAD(int[] nFG, int nXY, byte[] stateBuf);

    // 透射传感器校验指令
    public static native int Int_CheckHDSensor(int[] nFG, int nXY, int nSensorID, int nSensorNum);

    ////////////// 整机维护指令
    // XY老化测试
    public static native int Int_OldTest(int[] nFG, int nType, int nSpeed, int nNum);

    // 老化停止指令
    public static native int Int_StopOldTest(int[] nFG, int[] nNum);

    // 老化查询指令
    public static native int Int_CheckOldTest(int[] nFG, int[] nNum);

    // 传感器状态查询指令
    public static native int Int_FGSensorState(int[] nFG, int nType, byte[] stateBuf);

    // 齿轮顺序啮合测试启动
    public static native int Int_StartAutoGnaw(int[] nFG, int[] nInfoParam);

    // 齿轮顺序啮合测试停止
    public static native int Int_StopAutoGnaw(int[] nFG, int[] nInfoParam);

    // 模块测试指令
    public static native int Int_ModuleTest(int[] nFG, int[] nInfoParam);

    // 格口门控制指令
    public static native int Int_CtrlCupDoor(int[] nFG, int[] nGoodsParam, int nState);

    // 查询格口门状态指令
    public static native int Int_GetCupDoorState(int[] nFG, int[] nGoodsParam, int[] nState);

    // 控制格口柜蓝色指示灯指令
    public static native int Int_CrtlCupLightState(int[] nFG, int[] nGoodsParam, int nState);

    // 格口门控制流程
    public static native int Int_CtrlCupDoorPro(int[] nFG, int[] nGoodsParam, int[] stateBuf);

    ////////////// 电机板维护指令
    // 电机回原点指令
    public static native int Int_SetXYBackZero(int[] nFG, int nSpeed);

    // 电机回零点指令
    public static native int Int_SetXYBackIntZero(int[] nFG, int nSpeed);

    // 电机取货指令
    public static native int Int_GetXYGoods(int[] nFG, int nSpeed, int nLay, int nRow);

    // 电机送货指令
    public static native int Int_SetXYGoods(int[] nFG, int nSpeed);

    // 电机目标运行指令
    public static native int Int_SetXYLocation(int[] nFG, int[] nState);

    // 电机自检指令
    public static native int Int_XYAutoCheck(int[] nFG);

    // 电机使能控制指令
    public static native int Int_SetElcMacPow(int[] nFG, int nState);

    // 电机停止
    public static native int Int_XYStopElc(int[] nFG);

    // 电机运行状态查询指令
    public static native int Int_GetXYRunState(int[] nFG, byte[] uState);

    // 电机错误清除指令
    public static native int Int_CleanXY(int[] nFG);

    // 电机位置记录指令
    public static native int Int_SetXYLong(int[] nFG, int nLocation);

    // XY轴获取位置
    public static native int Int_GetXYPosition(int[] nFG, int[] positionBuf);

    // 电机补偿值设置指令
    public static native int Int_SetXYObjCompensate(int[] nFG, int nXY, int nType, int nRange);

    // 电机扫描传感器值查询指令
    public static native int Int_GetXYScanSensor(int[] nFG, int nLay, byte[] nStateBuf);

    // 电机位置设置指令
    public static native int Int_SetXYPosition(int[] nFG, int[] positionBuf);

    ////////////// 货斗板维护指令
    // 啮合电机控制指令
    public static native int Int_SetGnaw(int[] nFG, int nState, int nSpeed, int nTime);

    // 翻板开关指令
    public static native int Int_SetElcMac(int[] nFG, int nState, int nSpeed, int nRange);

    // 货斗运行状态查询指令
    public static native int Int_IfMacHand(int[] nFG, byte[] stateBuf);

    // 取货斗取货
    public static native int Int_SetMacHandGetGoods(int[] nFG, int nState, int nSpeed, int nRange);

    // 货斗激光测距传感器值查询指令
    public static native int Int_GetMacHandLaserSensorRange(int[] nFG, int[] nRange);

    ////////////// 中心主控板维护指令

    // 主控单元控制指令
    public static native int Int_ZMCtrl(int[] nFG, int nType, int nState);

    // 主控单元状态查询指令
    public static native int Int_ZMCtrlAct(int[] nFG, int nType, byte[] nState);

    // 光幕传感器校验指令
    public static native int Int_GMSensorCheckOut(int[] nFG, int nType, int nState);

    // 光幕传感器校验查询指令
    public static native int Int_GMSensorCheckOutAct(int[] nFG, int[] stateBuf);

    // 光幕传感器校验流程
    public static native int Int_GMSensorState(int[] nFG, int nType, int nState, int[] stateBuf);

    // 存在传感器校验指令
    public static native int Int_ExistSensorCheckOut(int[] nFG, int nType, int nState);

    // 存在传感器校验查询指令
    public static native int Int_ExistSensorCheckOutAct(int[] nFG, int[] stateBuf);

    // 存在传感器校验流程
    public static native int Int_ExistSensorState(int[] nFG, int nType, int nState, int[] stateBuf);

    ////////////// 制冷板维护指令
    // 机组运行状态查询
    public static native int Int_GetColdRunState(int[] nFG, int[] nState);

    // 压缩机参数设置
    public static native int Int_SetCompressor(int[] nFG, byte[] uParam);

    // 压缩机参数查询
    public static native int Int_GetCompressor(int[] nFG, byte[] uParam);

    // 化霜参数设置
    public static native int Int_SetDefrosting(int[] nFG, int[] nParam);

    // 化霜参数查询
    public static native int Int_GetDefrosting(int[] nFG, int[] nParam);

    // 制冷模块控制指令
    public static native int Int_SetColdModuleState(int[] nFG, int[] nState);

    // 传感器AD值获取
    public static native int Int_GetColdSensorAD(int[] nFG, int[] uParam);

    // 门体加热温湿度设置
    public static native int Int_SetColdDoorWetTemp(int[] nFG, int[] nParam);

    // 门体加热温湿度查询
    public static native int Int_GetColdDoorWetTemp(int[] nFG, int[] nParam);

    // 进入退出测试模式
    public static native int Int_SetColdTestModule(int[] nFG, int[] nParam);

    ////////////// 碳晶加热板维护指令
    // 设备运行状态查询
    public static native int Int_GetCarbonHeatState(int[] nFG, int[] nState);

    // 设备参数获取
    public static native int Int_GetCarbonHeatParam(int[] nFG, int[] nParam);

    ////////////// FS指令

    // FS 获取库数
    public static native int Int_GetstorHouseNum(int[] nFG, int[] nStorHosNum);

    // FS 照明模式设定 进维护
    public static native int Int_SetLightState(int[] nFG, int nLightState);

    // FS 照明模式获取
    public static native int Int_GetLightState(int[] nFG, int[] reLightState);

    // FS 节能运行模式设定 进维护
    public static native int Int_SetMoveState(int[] nFG, int nMoveState);

    // FS 节能运行模式获取
    public static native int Int_GetMoveState(int[] nFG, int[] reMoveState);

    // FS 时间带设定 进维护 '
    public static native int Int_SetSpaceTime(int[] nFG, byte[] nSpaceTime, int nNum);

    // FS 时间带获取
    public static native int Int_GetSpaceTime(int[] nFG, byte[] reSpaceTime);

    // FS 设定节能星期 进维护
    public static native int Int_SetSpaceWeek(int[] nFG, byte[] nSpaceWeek);

    // FS 获取节能星期
    public static native int Int_GetSpaceWeek(int[] nFG, byte[] reSpaceWeek);

    // FS 连续购买个数设定 进维护
    public static native int Int_SetContinuPurchNum(int[] nFG, int nNum);

    // FS 连续购买个数取得
    public static native int Int_GetContinuPurchNum(int[] nFG, int[] nNum);

    // FS 补货 进维护
    public static native int Int_SuplmentGoods(int[] nFG, int[] nGoodsParam);

    // FS 获取指定/全部货道所属库
    public static native int Int_GetstorHouse(int[] nFG, int[] nGoodsParam, int[] reState);

    // FS 获取指定/全部货道所属层
    public static native int Int_GetstorLay(int[] nFG, int[] nGoodsParam, int[] reState);

	static {
		System.loadLibrary("SimpleLogModule");
		System.loadLibrary("ConfigFileINI");
		System.loadLibrary("BCVMSDK_DE");
		System.loadLibrary("BCVMSDK_ZK");
	}
}
