package com.darly.snbc.adieas.base;

import android.os.RemoteException;

import com.darly.snbc.adieas.R;
import com.darly.snbc.adieas.common.BackeShow;
import com.snbc.bvm.bean.BaseInfo;

/**
 * C层通过JNI技术进行反射回调，满足回调机制
 * 包名称：com.darly.snbc.adieas.common
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/8 9:00
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class NdkReflect {
    private NdkReflect() {
    }
    private static class NdkReflectHodler {
        static NdkReflect instance = new NdkReflect();
    }
    public static NdkReflect getInstance() {
        return NdkReflectHodler.instance;
    }

    /**c层回调上来的方法，过程一回调
     * @param key 识别信息
     * @param code 返回编码信息
     */
    public void onStepOneCallBack(String key,int code) {
        try {
            BackeShow.getInstance().getCallBack(key).onInvokeStart();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    /**c层回调上来的方法，过程二回调
     * @param key 识别信息
     * @param code 返回编码信息
     */
    public void onStepTwoCallBack(String key,int code) {
        try {
            BaseInfo info = new BaseInfo();
            info.setCode(200);
            info.setMsg(key+"過程操作");
            BackeShow.getInstance().getCallBack(key).onInvokeSuccess(info);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    /**c层回调上来的方法，过程三回调
     * @param key 识别信息
     * @param code 返回编码信息
     */
    public void onStepThreeCallBack(String key,int code) {
        try {
            BaseInfo info = new BaseInfo();
            info.setCode(200);
            info.setMsg(key+"過程操作");
            BackeShow.getInstance().getCallBack(key).onInvokeFailed(info);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    /**c层回调上来的方法，过程四回调
     * @param key 识别信息
     * @param code 返回编码信息
     */
    public void onStepFourCallBack(String key,int code)  {
        BackeShow.getInstance().releaseCall(key);
    }
}
