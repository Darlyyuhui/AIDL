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

    BaseInfo BVMInitXYRoad(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMQueryInitResult(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMMoveSaleGoodsPro(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMCtrlSaleGoodsStepPro(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMStartShip(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMElecDoorCtrl(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMQueryBoxInfo(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMSetColdHeatModel(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMGetColdHeatModel(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMSetColdModel(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMGetColdMode(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMSetColdTemp(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMGetColdTemp(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMSetHeatTemp(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMGetHeatTemp(ParamerInfo paramer, SeverAidlCallBack callBack);

    BaseInfo BVMGetColdHeatTemp(ParamerInfo paramer, SeverAidlCallBack callBack);
}
