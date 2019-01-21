// ServerAidlInterface.aidl
package com.darly.snbc.adieas;
import com.darly.snbc.adieas.bean.BaseInfo;
import com.darly.snbc.adieas.bean.InParamer;
import com.darly.snbc.adieas.SeverAidlCallBack;
// Declare any non-default types here with import statements

interface ServerAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    BaseInfo onBinder(in InParamer paramer,in SeverAidlCallBack callback);
}
