package com.darly.snbc.jarlib.controller;

import android.text.TextUtils;

import com.darly.snbc.jarlib.bean.BaseResponse;
import com.darly.snbc.jarlib.common.JarlibListener;
import com.darly.snbc.jarlib.common.bolts.tasks.Task;
import com.darly.snbc.jarlib.common.bolts.tasks.iface.Continuation;

import org.json.JSONObject;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

import static android.os.AsyncTask.SERIAL_EXECUTOR;

/**
 * 调用示例类
 * 包名称：com.darly.snbc.jarlib.controller
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/6 9:14
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class AirController {

    @Deprecated
    public static int getDefault(int boxId){
        final int[] code = {-100};
        getDefault(boxId, new JarlibListener<BaseResponse>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(BaseResponse baseResponse) {

            }

            @Override
            public void onFailed(BaseResponse baseResponse) {
                code[0] = baseResponse.getCode();
            }
        });
        while (code[0] ==-100){

        }
        return code[0];
    }

    public static BaseResponse getDefault(int boxId, final JarlibListener<BaseResponse> listener){
        if (listener!=null){
            listener.onStart();
            Task.call(new Callable<String >() {
                @Override
                public String call() throws Exception {
                    //开始调用SO接口。
                    Thread.sleep(2000);
                    String  entity ="{code:2001,msg:}";
                    return entity;
                }
            }, SERIAL_EXECUTOR).continueWith(new Continuation<String, Boolean>() {
                @Override
                public Boolean then(Task<String> task) throws Exception {
                    if (TextUtils.isEmpty(task.getResult())) {
                        BaseResponse response = new BaseResponse();
                        response.setCode(2001);
                        response.setMsg("正常测试");
                        listener.onSuccess(response);
                    } else {
                        BaseResponse response = new BaseResponse();
                        response.setCode(2000);
                        response.setMsg("空值测试");
                        listener.onFailed(response);
                    }
                    return true;
                }
            },Task.UI_THREAD_EXECUTOR);
        }else {
            //开始调用SO接口。
            BaseResponse response = new BaseResponse();
            response.setCode(2001);
            response.setMsg("正常测试");
            return response;
        }
        return null;
    }


}
