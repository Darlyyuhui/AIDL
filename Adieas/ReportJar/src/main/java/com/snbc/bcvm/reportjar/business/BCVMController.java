package com.snbc.bcvm.reportjar.business;

import com.snbc.bcvm.reportjar.business.airconditioner.AirConditionManager;
import com.snbc.bcvm.reportjar.common.CommonConst;
import com.snbc.bcvm.reportjar.entity.ResponseEntity;
import com.snbc.bcvm.reportjar.entity.TempEntity;

/**
 * 适配老接口版本的整合类。
 * 包名称：com.snbc.bcvm.reportjar.business
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/12 9:10
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
@Deprecated
public class BCVMController {
    /**
     * 制冷制热模式设置
     *
     * @param boxid 副柜id
     * @param mode  0:常温模式 1：制冷模式 2：制热模式
     * @return 返回int类型数据
     */
    @Deprecated
    public static int BVMSetColdHeatModel(int boxid, int mode) {
        ResponseEntity<Integer> result = AirConditionManager.setColdHeatModelReport(boxid, mode);
        if (result.getCode() != CommonConst.ERROR_SUCCESS) {
            return result.getCode();
        }
        return CommonConst.ERROR_SUCCESS;
    }

    /**
     * 制冷制热模式查询
     *
     * @param boxid 副柜id
     * @return 返回int类型数据
     */
    @Deprecated
    public static int BVMGetColdHeatModel(int boxid) {
        ResponseEntity<Integer> result = AirConditionManager.getColdHeatModelReport(boxid);
        if (result.getCode() != CommonConst.ERROR_SUCCESS) {
            return result.getCode();
        }
        return result.getData();
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
        ResponseEntity<Integer> result = AirConditionManager.setColdModelReport(boxid, mode);
        if (result.getCode() != CommonConst.ERROR_SUCCESS) {
            return result.getCode();
        }
        return CommonConst.ERROR_SUCCESS;
    }

    /**
     * 查询制冷模式
     *
     * @param boxid 副柜id
     * @return 返回int类型数据
     */
    @Deprecated
    public static int BVMGetColdMode(int boxid) {
        ResponseEntity<Integer> result = AirConditionManager.getColdModelReport(boxid);
        if (result.getCode() != CommonConst.ERROR_SUCCESS) {
            return result.getCode();
        }
        return result.getData();
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
        ResponseEntity<Integer> result = AirConditionManager.setColdTempReport(boxid, onTemp, offTemp);
        if (result.getCode() != CommonConst.ERROR_SUCCESS) {
            return result.getCode();
        }
        return CommonConst.ERROR_SUCCESS;
    }

    /**
     * 制冷温度查询
     *
     * @param boxid 副柜id
     * @return 返回int[0]: 制冷ON温度 int[1] 制冷OFF温度
     */
    @Deprecated
    public static int[] BVMGetColdTemp(int boxid) {
        ResponseEntity<TempEntity> result = AirConditionManager.getColdTempReport(boxid);
        if (result.getCode() != CommonConst.ERROR_SUCCESS) {
            return new int[]{result.getCode()};
        }
        return new int[]{result.getData().getOnTemp(), result.getData().getOffTemp()};
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
        ResponseEntity<Integer> result = AirConditionManager.setHeatTempReport(boxid, onTemp, offTemp);
        if (result.getCode() != CommonConst.ERROR_SUCCESS) {
            return result.getCode();
        }
        return CommonConst.ERROR_SUCCESS;
    }

    /**
     * 制热温度查询
     *
     * @param boxid 副柜id
     * @return 返回int[0]: 制热ON温度 int[1] 制热OFF温度
     */
    @Deprecated
    public static int[] BVMGetHeatTemp(int boxid) {
        ResponseEntity<TempEntity> result = AirConditionManager.getHeatTempReport(boxid);
        if (result.getCode() != CommonConst.ERROR_SUCCESS) {
            return new int[]{result.getCode()};
        }
        return new int[]{result.getData().getOnTemp(), result.getData().getOffTemp()};
    }

    /**
     * 查询当前温度
     *
     * @param boxid 副柜id
     * @return int[0] :箱体温度 int[1]:外界环境温度
     */
    @Deprecated
    public static int[] BVMGetColdHeatTemp(int boxid) {
        ResponseEntity<TempEntity> result = AirConditionManager.getColdHeatTempReport(boxid);
        if (result.getCode() != CommonConst.ERROR_SUCCESS) {
            return new int[]{result.getCode()};
        }
        return new int[]{result.getData().getBoxTemp(), result.getData().getOutsideTemp()};
    }


}
