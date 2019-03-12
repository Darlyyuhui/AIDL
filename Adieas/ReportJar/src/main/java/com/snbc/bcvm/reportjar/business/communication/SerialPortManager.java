package com.snbc.bcvm.reportjar.business.communication;


import com.snbc.bcvm.BCVMZK;
import com.snbc.bcvm.reportjar.common.CheckoutUtil;
import com.snbc.bcvm.reportjar.common.CommonConst;
import com.snbc.bcvm.reportjar.entity.ResponseEntity;

/**
 * 通讯相关
 * 包名称：com.snbc.bcvm.bcvmcommon.communication
 * 作者：qinhaonan 项目名称：Vem
 * 日期：2019/1/24 15:38
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：qinhaonan@newbeiyang.com
 */

public class SerialPortManager {
    private static String TAG = SerialPortManager.class.getSimpleName();

    /**
     * @return ResponseEntity<Integer>
     * @Description : 	打开串口
     */
    public static ResponseEntity<Integer> openPort() {
        String result = BCVMZK.Int_IniZKComm();
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        CheckoutUtil.setCodeMessage(TAG, "openPort", result, responseEntity, responseEntity1 ->
                responseEntity1.setData(CommonConst.ERROR_SUCCESS));
        return responseEntity;
    }


    /**
     * @return ResponseEntity<Integer>
     * @Description : 	关闭串口
     */
    public static  ResponseEntity<Integer> closePort(){
        String result = BCVMZK.Int_CloseZKComm();
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        CheckoutUtil.setCodeMessage(TAG, "closePort", result, responseEntity, responseEntity1 ->
                responseEntity1.setData(CommonConst.ERROR_SUCCESS));
        return responseEntity;
    }

}

