package com.darly.snbc.adieas.common;

import android.text.TextUtils;

import com.darly.common.DLog;
import com.snbc.bvm.SeverAidlCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * 保存回调接口的单例类，方便级联使用。
 * 包名称：com.darly.snbc.adieas.common
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/12 11:20
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class CallBackController {
    private static CallBackController instance;
    private Map<String ,SeverAidlCallBack> callBackMap = new HashMap<String ,SeverAidlCallBack>();
    private CallBackController() {
    }
    private  static class CallBackControllerHolder{
        static CallBackController instance = new CallBackController();
    }
    public static CallBackController getInstance() {
        return CallBackControllerHolder.instance;
    }
    /** 存储上层应用传递的接口信息
     * @param key 识别信息
     * @param callBack 回调接口
     */
    public void setListener(String key,SeverAidlCallBack callBack) {
        if (!TextUtils.isEmpty(key)&&callBack!=null){
            callBackMap.put(key,callBack);
        }
    }
    /** 获取回调接口
     * @param key 识别信息
     * @return 返回接口对象
     */
    public SeverAidlCallBack getCallBack(String key) {
        if (callBackMap!=null&&callBackMap.containsKey(key)){
            return callBackMap.get(key);
        }
        return null;
    }
    /** 调用完成，移除接口对象
     * @param key 识别信息
     */
    public void releaseCall(String key) {
        if (callBackMap!=null&&callBackMap.containsKey(key)){
            callBackMap.remove(key);
            for (Map.Entry<String,SeverAidlCallBack> entry:callBackMap.entrySet()) {
                DLog.d(entry.getKey()+"--->"+entry.getValue());
            }
        }
    }

}
