package com.darly.snbc.adieas.ui.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONException;
import com.darly.snbc.adieas.common.CallBackController;
import com.snbc.bvm.ServerAidlInterface;
import com.snbc.bvm.SeverAidlCallBack;
import com.snbc.bvm.bean.BaseInfo;
import com.snbc.bvm.bean.InParamer;
import com.snbc.bvm.bean.ParamerInfo;

/**
 * 包名称：com.darly.snbc.adieas.ui.server
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/11 13:14
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ServerService extends Service {

    private ServerPresenter presenter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        presenter = new ServerPresenter();
        return server;
    }

    ServerAidlInterface.Stub server = new ServerAidlInterface.Stub() {

        @Override
        public BaseInfo onBinder(InParamer paramer, SeverAidlCallBack callback) throws RemoteException {
            if (paramer == null) {
                return presenter.emptyParam();
            } else {
                //在这里进行秘钥认证，认证不通过，无法去调用固件指令
                String param = paramer.getParamer();
                try {
                    ParamerInfo info = paramer.toParamerInfo(param);
                    if (info != null) {
                        //判断接口定义版本是否相同，版本不同情况下，验证失败。
                        if (presenter.isCurrentVersion(paramer.getId(), paramer.getVersion())) {
                            if (!TextUtils.isEmpty(paramer.getMethod().getDesc())) {
                                if (callback!=null) {
                                    CallBackController.getInstance().setListener(paramer.getMethod().getDesc(), callback);
                                }
                                return presenter.switchMethodCallBack(paramer.getMethod().getDesc(), info);
                            } else {
                                return presenter.emptyParam();
                            }
                        } else {
                            return presenter.errorVersion();
                        }
                    } else {
                        return presenter.errorKey();
                    }
                }catch (JSONException e){
                    return presenter.errorKey();
                }
            }
        }

        @Override
        public int Init() throws RemoteException {
            return -100101;
        }

    };

}
