package com.darly.snbc.readjar.back;

import android.text.TextUtils;


import com.darly.snbc.jarlib.common.bolts.tasks.Task;
import com.darly.snbc.jarlib.common.bolts.tasks.iface.Continuation;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 异步回调接口逻辑展示
 * 包名称：com.darly.snbc.readjar.back
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/4 10:20
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class AsynBack {

    final Executor SERIAL_EXECUTOR = Executors.newSingleThreadExecutor();
    private OnBackListener callBack;


    public void setOnBackListener(OnBackListener onBackListener) {
        this.callBack = onBackListener;
    }

    public AsynBack() {
    }

    public String AsynTask(){
        if (callBack!=null){
            callBack.onStart();
            Task.call(new Callable<String >() {
                @Override
                public String call() throws Exception {
                    //开始调用SO接口。
                    String  entity = "onBackListener != null";//BCVMZK.Int_GetHeatTemp(devInfo, revBuf);
                    //接口调用成功。针对entity进行解析
                    return entity;
                }
            }, SERIAL_EXECUTOR).continueWith(new Continuation<String, Boolean>() {
                @Override
                public Boolean then(Task<String> task) throws Exception {
                    //TODO 解析
                    if (TextUtils.isEmpty(task.getResult())) {//if (entity.getCode() == 99) {
                        callBack.onSuccess(task.getResult());
                    } else {
                        callBack.onFasle(task.getResult());
                    }
                    return true;
                }
            },Task.UI_THREAD_EXECUTOR);
        }else {
            //开始调用SO接口。
            String  entity = "onBackListener == null";//BCVMZK.Int_GetHeatTemp(devInfo, revBuf);
            //接口调用成功。针对entity进行解析
            return entity;
        }
        return null;
    }

}



