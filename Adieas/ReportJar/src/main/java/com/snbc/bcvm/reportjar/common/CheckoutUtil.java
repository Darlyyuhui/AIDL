package com.snbc.bcvm.reportjar.common;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.snbc.bcvm.reportjar.entity.ErrorEntity;
import com.snbc.bcvm.reportjar.entity.ResponseEntity;

import java.util.ArrayList;


/**
 * 检查工具
 * 包名称：com.snbc.bcvm.bcvmcommon.utils
 * 作者：qinhaonan 项目名称：Vem
 * 日期：2019/1/24 17:57
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：qinhaonan@newbeiyang.com
 */

public class CheckoutUtil {




    /**
     * 第一步,检查boxId是否合法
     * 第二步,如果boxId合法,调用setCodeMessage
     */
    public static <T> ResponseEntity<T> checkoutBoxId(String TAG, String fucName, int boxId, CheckoutIdSuccess<T> checkoutIdSuccess, ResponseSuccess<T> responseSuccess) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        if (boxId < 0) {
            responseEntity.setCode(CommonConst.ERROR_PARAM);
            responseEntity.setMessage("boxId 非法");
            responseEntity.setErrorEntity(getErrorEntity("入参错误", "boxId小于0", "传入正确的参数", "中间层"));
            Log.e(TAG, "Fail " + fucName + ",输出===>" + responseEntity.getMessage());
        } else {
            String json = checkoutIdSuccess.onSuccess(responseEntity);
            setCodeMessage(TAG, fucName, json, responseEntity, responseSuccess);
        }
        return responseEntity;
    }

    /**
     * 检查不含boxId时的参数检查封装
     */
    public static <T> ResponseEntity<T> checkoutParams(CheckoutParams checkoutJudge, String TAG, String fucName, ErrorEntity errorEntity, ResponseSuccess<T> responseSuccess) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        if (checkoutJudge.onJudge()) {
            String json = checkoutJudge.onSuccess();
            setCodeMessage(TAG, fucName, json, responseEntity, responseSuccess);
        } else {
            responseEntity.setCode(CommonConst.ERROR_PARAM);
            responseEntity.setMessage("入参非法");
            responseEntity.setErrorEntity(errorEntity);
            Log.e(TAG, "Fail " + fucName + ",输出===>" + responseEntity.getMessage());
        }
        return responseEntity;
    }

    /**
     * 检查调用so库是否返回成功
     */
    public static boolean checkoutSuccess(ResponseEntity responseEntity) {
        if (responseEntity.getErrorEntity() != null &&  //空指针异常保护
                responseEntity.getErrorEntity().getBody() != null &&
                responseEntity.getErrorEntity().getBody().getError() != null &&
                responseEntity.getErrorEntity().getBody().getError().size() > 0)
            return responseEntity.getErrorEntity().getBody().getError().get(0).getCode() == 99;
        else
            return false;
    }

    /**
     * 第一步 json解析
     * 第二步 json解析后如果成功设置ResponseEntity.code为99,
     */
    public static <T> ResponseEntity<T> setCodeMessage(String TAG, String fucName, String json, ResponseEntity<T> responseEntity, ResponseSuccess<T> responseSuccess) {
        Log.i(TAG, "so库返回的json" + fucName + ",输出===>" + json);
        parseObject(json, responseEntity);//json解析
        if (checkoutSuccess(responseEntity)) {
            responseSuccess.onSuccess(responseEntity);
            responseEntity.setCode(CommonConst.ERROR_SUCCESS);
            responseEntity.setMessage("调用so库成功");
            Log.i(TAG, "Success " + fucName + ",输出===>" + responseEntity.getMessage());
        } else {
            if (responseEntity.getCode() != CommonConst.ERROR_PARSE_JSON) {//直接返回json解析失败
                responseEntity.setCode(CommonConst.ERROR_OPENCOM);//code不等于99时,查看errorEntity对象
                responseEntity.setMessage("调用so库失败");
                for (int i = 0; i < responseEntity.getErrorEntity().getBody().getError().size(); i++) {
                    Log.e(TAG, "Fail " + fucName + "输出" + i + "===>" + JSON.toJSONString(responseEntity.getErrorEntity().getBody().getError().get(i)));
                }
            } else {
                Log.e(TAG, "Fail " + fucName + ",输出===>" + responseEntity.getMessage()); //json解析失败打印log
            }
        }
        return responseEntity;
    }

    /**
     * json解析
     */
    static <T> ResponseEntity<T> parseObject(String result, ResponseEntity<T> responseEntity) {
        try {
            ErrorEntity errorEntity = JSON.parseObject(result, new TypeReference<ErrorEntity>() {
            });
            responseEntity.setErrorEntity(errorEntity);
            return responseEntity;
        } catch (Exception e) {
            responseEntity.setCode(CommonConst.ERROR_PARSE_JSON);
            responseEntity.setMessage("json解析失败了");
            return responseEntity;
        }
    }

    /**
     * 封装一个ErrorEntity
     *
     * @return
     */
    public static ErrorEntity getErrorEntity(String display, String reason, String solution, String system) {
        ErrorEntity errorEntity = new ErrorEntity();
        ErrorEntity.HeaderBean headerBean = new ErrorEntity.HeaderBean();
        headerBean.setId("SNBC");
        headerBean.setVersion("");
        ErrorEntity.BodyBean bodyBean = new ErrorEntity.BodyBean();
        ArrayList<ErrorEntity.BodyBean.ErrorBean> errorBeanList = new ArrayList<>();
        ErrorEntity.BodyBean.ErrorBean errorBean = new ErrorEntity.BodyBean.ErrorBean(CommonConst.ERROR_PARAM,
                display, reason, solution, system);
        errorBeanList.add(errorBean);
        bodyBean.setError(errorBeanList);
        errorEntity.setBody(bodyBean);
        errorEntity.setHeader(headerBean);
        return errorEntity;
    }

}
