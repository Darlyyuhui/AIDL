package com.snbc.bcvm.reportjar.business;

import com.snbc.bcvm.BCVMZK;
import com.snbc.bcvm.reportjar.common.CheckoutUtil;
import com.snbc.bcvm.reportjar.common.CommonConst;
import com.snbc.bcvm.reportjar.entity.ResponseEntity;

/**
 * 针对Server和Driver提供的调用控制类（Server和Driver提供时不进行混淆。）
 * 包名称：com.snbc.bcvm.reportjar.business
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/11 16:06
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ManagerController {

    private static String Tag = ManagerController.class.getSimpleName();

    /**
     * @param boxId： 副柜id
     * @return ResponseEntity<Integer> 0：常温模式 1：制冷模式 2：制热模式 3：智能模式
     * @Description : 	制冷制热模式查询
     */
    @Deprecated
    public static int GetColdHeatModel(int boxId) {
        ResponseEntity<Integer> result = getColdHeatModel(boxId);
        if (result.getCode() == CommonConst.ERROR_SUCCESS){
            return result.getData();
        }else {
            result.getErrorEntity().getBody().getError().get(0).getCode();
            return result.getCode();
        }
    }

    /**
     * @param boxId： 副柜id
     * @return ResponseEntity<Integer> 0：常温模式 1：制冷模式 2：制热模式 3：智能模式
     * @Description : 	制冷制热模式查询
     */
    public static ResponseEntity<Integer> getColdHeatModel(int boxId) {
        int[] devInfo = new int[CommonConst.DEV_ARRAY_LENGTH];
        int[] devBuf = new int[CommonConst.ARRAY_LENGTH];
        devInfo[0] = boxId;
        devInfo[1] = CommonConst.depot;
        return CheckoutUtil.checkoutBoxId(Tag, "getColdHeatModel",
                boxId, responseEntity -> BCVMZK.Int_GetColdHeatModel(devInfo, devBuf),
                responseEntity -> responseEntity.setData(devBuf[0]));
    }


}
