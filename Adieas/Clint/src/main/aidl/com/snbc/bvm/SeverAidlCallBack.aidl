// SeverAidlCallBack.aidl
package com.snbc.bvm;
import com.snbc.bvm.bean.BaseInfo;
// Declare any non-default types here with import statements
interface SeverAidlCallBack {
    void onInvokeStart();
    void onInvokeSuccess(in BaseInfo info);
    void onInvokeFailed(in BaseInfo info);
}
