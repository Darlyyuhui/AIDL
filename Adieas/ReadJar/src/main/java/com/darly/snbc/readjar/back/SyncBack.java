package com.darly.snbc.readjar.back;

/**
 * 同步步回调接口逻辑展示
 * 包名称：com.darly.snbc.readjar.back
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/3/4 10:21
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class SyncBack {
    private OnBackListener onBackListener;

    public void setOnBackListener(OnBackListener onBackListener) {
        this.onBackListener = onBackListener;
    }

    public SyncBack() {
    }

    public String  SyncTask(){
        if (onBackListener==null){
            return null;
        }
        onBackListener.onStart();
        //开始调用SO接口。
        String  entity = "";//BCVMZK.Int_GetHeatTemp(devInfo, revBuf);
        //接口调用成功。针对entity进行解析
        //TODO 解析
        if (entity == ""){//if (entity.getCode() == 99) {
            onBackListener.onSuccess(entity);
            return entity;
        } else {
            onBackListener.onFasle(entity);
            return entity;
        }
    }
}
