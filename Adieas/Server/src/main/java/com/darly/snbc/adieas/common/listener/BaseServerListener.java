package com.darly.snbc.adieas.common.listener;

import com.snbc.bvm.SeverAidlCallBack;
import com.snbc.bvm.bean.BaseInfo;
import com.snbc.bvm.bean.ParamerInfo;

/**
 * 基础接口调用方案。
 * 包名称：com.darly.snbc.adieas.common.listener
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/14 11:34
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public interface BaseServerListener {
    BaseInfo paramerEmpty();

    BaseInfo BVMInit(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMOpenPort(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMClosePort(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMGetRunningState(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMGetDoorState(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMGetFGFault(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMCleanSysFault(ParamerInfo paramer, SeverAidlCallBack callBack);

    //货道扫描
    BaseInfo BVMInitXYRoad(ParamerInfo paramer, SeverAidlCallBack callBack);
}
