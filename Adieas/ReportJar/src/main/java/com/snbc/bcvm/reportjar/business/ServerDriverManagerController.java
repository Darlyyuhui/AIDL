package com.snbc.bcvm.reportjar.business;

import com.snbc.bcvm.reportjar.business.airconditioner.AirConditionManager;
import com.snbc.bcvm.reportjar.entity.ResponseEntity;
import com.snbc.bcvm.reportjar.entity.TempEntity;

/**
 * 针对Server和Driver提供的调用控制类（Server和Driver提供时不进行混淆。）
 * 包名称：com.snbc.bcvm.reportjar.business
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/11 16:06
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ServerDriverManagerController {

    public static void BVMInit() {

    }

    public static void GetFactoryCode() {
    }

    public static void BVMInitXYRoad() {
    }

    public static void BVMQueryInitResult() {
    }

    public static void BVMMoveSaleGoodsPro() {
    }

    public static void BVMCtrlSaleGoodsStepPro() {
    }

    public static void BVMStartShip() {
    }


    public static void BVMElecDoorCtrl() {
    }

    public static void BVMQueryBoxInfo() {
    }

    /**
     * 制冷制热模式设置
     *
     * @param boxid 副柜id
     * @param mode  0:常温模式 1：制冷模式 2：制热模式
     * @return 返回int类型数据
     */
    @Deprecated
    public static int BVMSetColdHeatModel(int boxid, int mode) {
        return BCVMController.BVMSetColdHeatModel(boxid, mode);
    }

    /**
     * 设置制冷制热模式
     *
     * @param boxId：副柜id
     * @param mode：0:常温模式 1：制冷模式 2：制热模式
     * @return ResponseEntity<Integer>
     */
    public static ResponseEntity<Integer> setColdHeatModelReport(int boxId, int mode) {
        return AirConditionManager.setColdHeatModelReport(boxId, mode);
    }

    /**
     * 制冷制热模式查询
     *
     * @param boxid 副柜id
     * @return 返回int类型数据
     */
    @Deprecated
    public static int BVMGetColdHeatModel(int boxid) {
        return BCVMController.BVMGetColdHeatModel(boxid);
    }

    /**
     * 制冷制热模式查询
     *
     * @param boxId： 副柜id
     * @return ResponseEntity<Integer> 0：常温模式 1：制冷模式 2：制热模式 3：智能模式
     */
    public static ResponseEntity<Integer> getColdHeatModelReport(int boxId) {
        return AirConditionManager.getColdHeatModelReport(boxId);
    }

    /**
     * 制冷模式设置
     *
     * @param boxid 副柜id
     * @param mode  0:常温模式 1：制冷模式 2：制热模式
     * @return 返回int类型数据
     */
    @Deprecated
    public static int BVMSetColdModel(int boxid, int mode) {
        return BCVMController.BVMSetColdModel(boxid, mode);
    }

    /**
     * 制冷模式设置
     *
     * @param boxId：副柜id
     * @param mode：0：弱冷模式，1：强冷模式
     * @return ResponseEntity<Integer>
     */
    public static ResponseEntity<Integer> setColdModelReport(int boxId, int mode) {
        return AirConditionManager.setColdModelReport(boxId, mode);
    }

    /**
     * 查询制冷模式
     *
     * @param boxid 副柜id
     * @return 返回int类型数据
     */
    @Deprecated
    public static int BVMGetColdMode(int boxid) {
        return BCVMController.BVMGetColdMode(boxid);
    }

    /**
     * 查询制冷模式
     *
     * @param boxId： 副柜id
     * @return ResponseEntity<Integer>
     */
    public static ResponseEntity<Integer> getColdModelReport(int boxId) {
        return AirConditionManager.getColdModelReport(boxId);
    }

    /**
     * 制冷温度设置 on>off ,温差必须大于等于4度
     *
     * @param boxid   副柜id
     * @param onTemp  制冷ON温度 范围4-25度
     * @param offTemp 制冷OFF温度 范围4-25度
     * @return 返回int类型数据
     */
    @Deprecated
    public static int BVMSetColdTemp(int boxid, int onTemp, int offTemp) {
        return BCVMController.BVMSetColdTemp(boxid, onTemp, offTemp);
    }

    /**
     * 制冷温度设置
     *
     * @param boxId： boxid:副柜id
     *               onTemp：制冷ON温度 范围4-25度
     *               offTemp：制冷OFF温度 范围4-25度
     *               on>off ,温差必须大于等于4度
     * @return ResponseEntity<Integer>
     */
    public static ResponseEntity<Integer> setColdTempReport(int boxId, int onTemp, int offTemp) {
        return AirConditionManager.setColdTempReport(boxId, onTemp, offTemp);
    }

    /**
     * 制冷温度查询
     *
     * @param boxid 副柜id
     * @return 返回int[0]: 制冷ON温度 int[1] 制冷OFF温度
     */
    @Deprecated
    public static int[] BVMGetColdTemp(int boxid) {
        return BCVMController.BVMGetColdTemp(boxid);
    }

    /**
     * 制冷温度查询
     *
     * @param boxId： boxid:副柜id
     * @return ResponseEntity<ColdTempEntity> 见 ColdTempEntity类
     */
    public static ResponseEntity<TempEntity> getColdTempReport(int boxId) {
        return AirConditionManager.getColdTempReport(boxId);
    }

    /**
     * 制热温度设置  on<off   温差必须大于等于4度
     *
     * @param boxid   副柜id
     * @param onTemp  制冷ON温度 范围4-25度
     * @param offTemp 制冷OFF温度 范围4-25度
     * @return 返回int类型数据
     */
    @Deprecated
    public static int BVMSetHeatTemp(int boxid, int onTemp, int offTemp) {
        return BCVMController.BVMSetHeatTemp(boxid, onTemp, offTemp);
    }

    /**
     * 制热温度设置
     *
     * @param boxId： boxid:副柜id
     *               onTemp：制冷ON温度 范围4-25度
     *               offTemp：制冷OFF温度 范围4-25度
     *               on<off   温差必须大于等于4度
     * @return ResponseEntity<Integer>
     */
    public static ResponseEntity<Integer> setHeatTempReport(int boxId, int onTemp, int offTemp) {
        return AirConditionManager.setHeatTempReport(boxId, onTemp, offTemp);
    }

    /**
     * 制热温度查询
     *
     * @param boxid 副柜id
     * @return 返回int[0]: 制热ON温度 int[1] 制热OFF温度
     */
    @Deprecated
    public static int[] BVMGetHeatTemp(int boxid) {
        return BCVMController.BVMGetHeatTemp(boxid);
    }

    /**
     * 制热温度查询
     *
     * @param boxId： boxid:副柜id
     * @return ResponseEntity<HeatTempEntity> 见 ColdTempEntity类
     */
    public static ResponseEntity<TempEntity> getHeatTempReport(int boxId) {
        return AirConditionManager.getHeatTempReport(boxId);
    }

    /**
     * 查询当前温度
     *
     * @param boxid 副柜id
     * @return int[0] :箱体温度 int[1]:外界环境温度
     */
    @Deprecated
    public static int[] BVMGetColdHeatTemp(int boxid) {
        return BCVMController.BVMGetColdHeatTemp(boxid);
    }

    /**
     * 查询当前温度
     *
     * @param boxId： boxid:副柜id
     * @return ResponseEntity<CurrentTempEntity> 见 ColdTempEntity类
     */
    public static ResponseEntity<TempEntity> getColdHeatTempReport(int boxId) {
        return AirConditionManager.getColdHeatTempReport(boxId);
    }
}
