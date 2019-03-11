// ServerAidlInterface.aidl
package com.snbc.bvm;
import com.snbc.bvm.bean.BaseInfo;
import com.snbc.bvm.bean.InParamer;
import com.snbc.bvm.SeverAidlCallBack;
// Declare any non-default types here with import statements

interface ServerAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    BaseInfo onBinder(in InParamer paramer,in SeverAidlCallBack callback);

    int Init(in String key);


}
