package com.darly.snbc.adieas.common.listener;

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

    BaseInfo BVMInit(ParamerInfo paramer);

    BaseInfo BVMInitXYRoad(ParamerInfo paramer);

    BaseInfo BVMQueryInitResult(ParamerInfo paramer);

    BaseInfo BVMMoveSaleGoodsPro(ParamerInfo paramer);

    BaseInfo BVMCtrlSaleGoodsStepPro(ParamerInfo paramer);

    BaseInfo BVMStartShip(ParamerInfo paramer);

    BaseInfo BVMElecDoorCtrl(ParamerInfo paramer);

    BaseInfo BVMQueryBoxInfo(ParamerInfo paramer);

    BaseInfo BVMSetColdHeatModel(ParamerInfo paramer);

    BaseInfo BVMGetColdHeatModel(ParamerInfo paramer);

    BaseInfo BVMSetColdModel(ParamerInfo paramer);

    BaseInfo BVMGetColdMode(ParamerInfo paramer);

    BaseInfo BVMSetColdTemp(ParamerInfo paramer);

    BaseInfo BVMGetColdTemp(ParamerInfo paramer);

    BaseInfo BVMSetHeatTemp(ParamerInfo paramer);

    BaseInfo BVMGetHeatTemp(ParamerInfo paramer);

    BaseInfo BVMGetColdHeatTemp(ParamerInfo paramer);
}
