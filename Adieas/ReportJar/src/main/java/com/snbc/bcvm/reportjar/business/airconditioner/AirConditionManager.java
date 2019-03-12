package com.snbc.bcvm.reportjar.business.airconditioner;


import com.snbc.bcvm.BCVMZK;
import com.snbc.bcvm.reportjar.common.CheckoutUtil;
import com.snbc.bcvm.reportjar.entity.ResponseEntity;
import com.snbc.bcvm.reportjar.entity.TempEntity;

import static com.snbc.bcvm.reportjar.common.CheckoutUtil.checkoutBoxId;
import static com.snbc.bcvm.reportjar.common.CommonConst.ARRAY_LENGTH;
import static com.snbc.bcvm.reportjar.common.CommonConst.DEV_ARRAY_LENGTH;
import static com.snbc.bcvm.reportjar.common.CommonConst.ERROR_PARAM;
import static com.snbc.bcvm.reportjar.common.CommonConst.ERROR_SUCCESS;
import static com.snbc.bcvm.reportjar.common.CommonConst.depot;

/**
 * 制冷制热相关
 * 包名称：com.snbc.bcvm.bcvmcommon.airconditioner
 * 作者：qinhaonan 项目名称：Vem
 * 日期：2019/1/24 15:42
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：qinhaonan@newbeiyang.com
 */

public class AirConditionManager {
    private static String TAG = AirConditionManager.class.getSimpleName();

    /**
     * @param boxId：副柜id
     * @param mode：0:常温模式 1：制冷模式 2：制热模式
     * @return ResponseEntity<Integer>
     * @Description : 	设置制冷制热模式
     */
    public static ResponseEntity<Integer> setColdHeatModelReport(int boxId, int mode) {
        int[] devInfo = new int[DEV_ARRAY_LENGTH];
        devInfo[0] = boxId;
        devInfo[1] = depot;
        return checkoutBoxId(TAG,
                "setColdHeatModelReport",
                boxId,
                responseEntity -> BCVMZK.Int_SetColdHeatModel(devInfo, mode),
                responseEntity -> responseEntity.setData(ERROR_SUCCESS));
    }

    /**
     * @param boxId： 副柜id
     * @return ResponseEntity<Integer> 0：常温模式 1：制冷模式 2：制热模式 3：智能模式
     * @Description : 	制冷制热模式查询
     */
    public static ResponseEntity<Integer> getColdHeatModelReport(int boxId) {
        int[] devInfo = new int[DEV_ARRAY_LENGTH];
        int[] devBuf = new int[ARRAY_LENGTH];
        devInfo[0] = boxId;
        devInfo[1] = depot;
        return checkoutBoxId(TAG, "getColdHeatModelReport",
                boxId, responseEntity -> BCVMZK.Int_GetColdHeatModel(devInfo, devBuf),
                responseEntity -> responseEntity.setData(devBuf[0]));
    }



    /**
     * @param boxId：副柜id
     * @param mode：0：弱冷模式，1：强冷模式
     * @return ResponseEntity<Integer>
     * @Description : 	制冷模式设置
     */
    public static ResponseEntity<Integer> setColdModelReport(int boxId, int mode) {
        int[] devInfo = new int[DEV_ARRAY_LENGTH];
        devInfo[0] = boxId;
        devInfo[1] = depot;
        return checkoutBoxId(TAG,
                "setColdModelReport",
                boxId,
                responseEntity -> BCVMZK.Int_SetColdModel(devInfo, mode),
                responseEntity -> responseEntity.setData(ERROR_SUCCESS));
    }

    /**
     * @param boxId： 副柜id
     * @return ResponseEntity<Integer>
     * @Description : 	查询制冷模式
     */
    public static ResponseEntity<Integer> getColdModelReport(int boxId) {
        int[] devInfo = new int[DEV_ARRAY_LENGTH];
        int[] revBuf = new int[ARRAY_LENGTH];
        devInfo[0] = boxId;
        devInfo[1] = depot;
        return checkoutBoxId(TAG,
                "getColdModelReport",
                boxId,
                responseEntity -> BCVMZK.Int_GetColdModel(devInfo, revBuf),
                responseEntity -> responseEntity.setData(revBuf[0])
        );
    }

    /**
     * @param boxId： boxid:副柜id
     *               onTemp：制冷ON温度 范围4-25度
     *               offTemp：制冷OFF温度 范围4-25度
     *               on>off ,温差必须大于等于4度
     * @return ResponseEntity<Integer>
     * @Description : 	制冷温度设置
     */
    public static ResponseEntity<Integer> setColdTempReport(int boxId, int onTemp, int offTemp) {
        if (4 <= onTemp && onTemp <= 25 &&
                4 <= offTemp && offTemp <= 25 &&
                onTemp - offTemp>=4) {
            int[] devInfo = new int[DEV_ARRAY_LENGTH];
            devInfo[0] = boxId;
            devInfo[1] = depot;
            int[] devParam = new int[2];
            devParam[0] = onTemp;
            devParam[1] = offTemp;
            return checkoutBoxId(TAG, "setColdTempReport",
                    boxId,
                    responseEntity -> BCVMZK.Int_SetColdTemp(devInfo, devParam),
                    responseEntity -> responseEntity.setData(ERROR_SUCCESS));
        } else {
            ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
            responseEntity.setCode(ERROR_PARAM);
            responseEntity.setMessage("温度参数非法");
            responseEntity.setErrorEntity(CheckoutUtil.getErrorEntity("入参错误",
                    "温度 范围4-25度且onTemp>offTemp,温差必须大于等于4度",
                    "传入正确的参数",
                    "中间层"));
            return responseEntity;
        }
    }

    /**
     * @param boxId： boxid:副柜id
     * @return ResponseEntity<ColdTempEntity> 见 ColdTempEntity类
     * @Description : 	制冷温度查询
     */
    public static ResponseEntity<TempEntity> getColdTempReport(int boxId) {
        int[] devInfo = new int[DEV_ARRAY_LENGTH];
        int[] revBuf = new int[ARRAY_LENGTH];

        devInfo[0] = boxId;
        devInfo[1] = depot;
        return checkoutBoxId(TAG,
                "getColdTempReport",
                boxId,
                responseEntity -> BCVMZK.Int_GetColdTemp(devInfo, revBuf),
                responseEntity -> {
                    TempEntity coldTempEntity = new TempEntity();
                    coldTempEntity.setOnTemp(revBuf[0]);
                    coldTempEntity.setOffTemp(revBuf[1]);
                    responseEntity.setData(coldTempEntity);
                });
    }

    /**
     * @param boxId： boxid:副柜id
     *               onTemp：制冷ON温度 范围4-25度
     *               offTemp：制冷OFF温度 范围4-25度
     *               on<off   温差必须大于等于4度
     * @return ResponseEntity<Integer>
     * @Description : 	制热温度设置
     */
    public static ResponseEntity<Integer> setHeatTempReport(int boxId, int onTemp, int offTemp) {

        if(4 <= onTemp && onTemp <= 25 &&
                4 <= offTemp && offTemp <= 25 &&
                offTemp - onTemp>=4){
            int[] devInfo = new int[DEV_ARRAY_LENGTH];
            devInfo[0] = boxId;
            devInfo[1] = depot;
            int[] devParam = new int[2];
            devParam[0] = onTemp;
            devParam[1] = offTemp;
            return checkoutBoxId(TAG,
                    "setHeatTempReport",
                    boxId,
                    responseEntity -> BCVMZK.Int_SetHeatTemp(devInfo, devParam),
                    responseEntity -> responseEntity.setData(ERROR_SUCCESS));
        }else {
            ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
            responseEntity.setCode(ERROR_PARAM);
            responseEntity.setMessage("温度参数非法");
            responseEntity.setErrorEntity(CheckoutUtil.getErrorEntity("入参错误",
                    "温度 范围4-25度且on<off 温差必须大于等于4度",
                    "传入正确的参数",
                    "中间层"));
            return responseEntity;
        }
    }


    /**
     * @param boxId： boxid:副柜id
     * @return ResponseEntity<HeatTempEntity> 见 ColdTempEntity类
     * @Description : 	制热温度查询
     */
    public static ResponseEntity<TempEntity> getHeatTempReport(int boxId) {
        int[] devInfo = new int[DEV_ARRAY_LENGTH];
        int[] revBuf = new int[ARRAY_LENGTH];

        devInfo[0] = boxId;
        devInfo[1] = depot;
        return checkoutBoxId(TAG, "getHeatTempReport",
                boxId,
                responseEntity -> BCVMZK.Int_GetHeatTemp(devInfo, revBuf),
                responseEntity -> {
                    TempEntity heatTempEntity = new TempEntity();
                    heatTempEntity.setOnTemp(revBuf[0]);
                    heatTempEntity.setOffTemp(revBuf[1]);
                    responseEntity.setData(heatTempEntity);
                });
    }

    /**
     * @param boxId： boxid:副柜id
     * @return ResponseEntity<CurrentTempEntity> 见 ColdTempEntity类
     * @Description : 	查询当前温度
     */
    public static ResponseEntity<TempEntity> getColdHeatTempReport(int boxId) {
        int[] devInfo = new int[DEV_ARRAY_LENGTH];
        int[] revBuf = new int[ARRAY_LENGTH];

        devInfo[0] = boxId;
        devInfo[1] = depot;
        return checkoutBoxId(TAG, "getCurrentTempReport",
                boxId,
                responseEntity -> BCVMZK.Int_GetHeatTemp(devInfo, revBuf),
                responseEntity -> {
                    TempEntity currentTempEntity = new TempEntity();
                    currentTempEntity.setBoxTemp(revBuf[0]);
                    currentTempEntity.setOutsideTemp(revBuf[1]);
                    responseEntity.setData(currentTempEntity);
                });
    }
}
