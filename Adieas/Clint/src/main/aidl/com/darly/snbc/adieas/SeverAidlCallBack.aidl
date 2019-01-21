// SeverAidlCallBack.aidl
package com.darly.snbc.adieas;
import com.darly.snbc.adieas.bean.BaseInfo;
// Declare any non-default types here with import statements
interface SeverAidlCallBack {
    void onInvokeStart();
    void onInvokeSuccess(in BaseInfo info);
    void onInvokeFailed(in BaseInfo info);
}
