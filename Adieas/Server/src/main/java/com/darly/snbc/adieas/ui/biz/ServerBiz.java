package com.darly.snbc.adieas.ui.biz;

import com.alibaba.fastjson.JSON;
import com.darly.common.DLog;
import com.darly.common.retrofit.RxjavaRetrofitRequestUtil;
import com.darly.snbc.adieas.R;
import com.darly.snbc.adieas.base.BaseApplication;
import com.darly.snbc.adieas.common.listener.BaseServerListener;
import com.darly.snbc.adieas.common.retrofit.HttpInterface;
import com.google.gson.JsonObject;
import com.snbc.bcvm.NdkReflect;
import com.snbc.bcvm.reportjar.business.ServerDriverManagerController;
import com.snbc.bcvm.reportjar.entity.ErrorEntity;
import com.snbc.bcvm.reportjar.entity.ResponseEntity;
import com.snbc.bcvm.reportjar.entity.TempEntity;
import com.snbc.bvm.bean.BaseInfo;
import com.snbc.bvm.bean.ErrorInfo;
import com.snbc.bvm.bean.ParamerInfo;
import com.snbc.bvm.bean.ResultInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 实现接口调用逻辑
 * 包名称：com.darly.snbc.adieas.ui.biz
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/14 13:28
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ServerBiz implements BaseServerListener {

    @Override
    public BaseInfo paramerEmpty() {
        DLog.d(getClass().getSimpleName() + "paramerEmpty");
        BaseInfo info = new BaseInfo();
        info.setCode(-40031);
        info.setMsg(BaseApplication.getMessage(R.string.st_null_paramer));
        return info;
    }

    @Override
    public BaseInfo BVMInit(ParamerInfo paramer) {
        DLog.d(getClass().getSimpleName(), "BVMInit");
        BaseInfo info = new BaseInfo();
        ResultInfo result = new ResultInfo();
        String factoryCode = "BLOK-NN114FSOO0507002053";

        RxjavaRetrofitRequestUtil.getInstance().get(HttpInterface.class).getSign()
                .subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<JsonObject, String>() {
                    @Override
                    public String call(JsonObject s) {
                        DLog.json("Func1", s.toString());
                        return s.toString();
                    }
                })
                .subscribe(new Observer<String>() {
                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                               }

                               @Override
                               public void onNext(String data) {
                               }
                           }

                );
        return info;

    }


    @Override
    public BaseInfo BVMInitXYRoad(ParamerInfo paramer) {
        DLog.d(getClass().getSimpleName(), "BVMInitXYRoad");
        BaseInfo info = new BaseInfo();
        ResultInfo result = new ResultInfo();
        ArrayList<String> list = new ArrayList<>();

        if (new Random().nextBoolean()) {
            info.setCode(200);
            info.setMsg("BVMInitXYRoad" + BaseApplication.getMessage(R.string.st_opt_success));
            list.add("BVMInitXYRoad 测试信息");
            result.setResult(list);
            info.setBody(result.toJson());
            return info;
        } else {
            info.setCode(120);
            info.setMsg("BVMInitXYRoad" + BaseApplication.getMessage(R.string.st_opt_failed));
            return info;
        }
    }

    @Override
    public BaseInfo BVMQueryInitResult(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMMoveSaleGoodsPro(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMCtrlSaleGoodsStepPro(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMStartShip(ParamerInfo paramer) {
        DLog.d(getClass().getSimpleName(), "BVMStartShip");
        BaseInfo info = new BaseInfo();
        startCheck("BVMStartShip");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        info.setCode(200);
        info.setMsg("BVMStartShip" + BaseApplication.getMessage(R.string.st_opt_start));
        return info;
    }

    @Override
    public BaseInfo BVMElecDoorCtrl(ParamerInfo paramer) {
        DLog.d(getClass().getSimpleName(), "BVMElecDoorCtrl");
        BaseInfo info = new BaseInfo();
        startCheck("BVMElecDoorCtrl");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        info.setCode(200);
        info.setMsg("BVMElecDoorCtrl" + BaseApplication.getMessage(R.string.st_opt_start));
        return info;
    }



    @Override
    public BaseInfo BVMQueryBoxInfo(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMSetColdHeatModel(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMGetColdHeatModel(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMSetColdModel(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMGetColdMode(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMSetColdTemp(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMGetColdTemp(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMSetHeatTemp(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMGetHeatTemp(ParamerInfo paramer) {
        return null;
    }

    @Override
    public BaseInfo BVMGetColdHeatTemp(ParamerInfo paramer) {
        DLog.d(getClass().getSimpleName(), "BVMGetColdHeatTemp");
        startCheck("BVMGetColdHeatTemp");
        //进行参数判断
        BaseInfo info = new BaseInfo();
        ResultInfo result = new ResultInfo();
        ArrayList<String> resultList = new ArrayList<>();
        result.setResult(resultList);
        ArrayList<ErrorInfo> errorList = new ArrayList<>();
        result.setError(errorList);
        //调用执行
        ResponseEntity<TempEntity> entity = ServerDriverManagerController.getColdHeatTempReport(paramer.getBoxid());
        info.setCode(entity.getCode());
        if (entity.getCode() == 99) {
            TempEntity succ = entity.getData();
            resultList.add(JSON.toJSONString(succ));
            info.setMsg(entity.getMessage());
        } else {
            //返回so库错误信息（预留）
            List<ErrorEntity.BodyBean.ErrorBean> errorEntities = entity.getErrorEntity().getBody().getError();
            errorSet(errorList, errorEntities);
        }
        //进行信息加密;
        info.setBody(result.toJson());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return info;
    }


    private void startCheck(final String key) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    NdkReflect.getInstance().onStepOneCallBack(key,0);
                    Thread.sleep(1000);
                    DLog.d(getClass().getSimpleName(), key+Thread.currentThread()+"Thread.sleep(1000);");
                    NdkReflect.getInstance().onStepTwoCallBack(key,11);
                    Thread.sleep(1500);
                    DLog.d(getClass().getSimpleName(), key+Thread.currentThread()+"Thread.sleep(1500);");
                    NdkReflect.getInstance().onStepThreeCallBack(key,12);
                    Thread.sleep(2000);
                    DLog.d(getClass().getSimpleName(), key+Thread.currentThread()+"Thread.sleep(2000);");
                    NdkReflect.getInstance().onStepFourCallBack(key,-1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void errorSet(ArrayList<ErrorInfo> errorList, List<ErrorEntity.BodyBean.ErrorBean> errorEntities) {
        if (errorEntities != null) {
            for (ErrorEntity.BodyBean.ErrorBean bean : errorEntities) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setCode(bean.getCode());
                errorInfo.setDisplay(bean.getDisplay());
                errorInfo.setReason(bean.getReason());
                errorInfo.setSolution(bean.getSolution());
                errorInfo.setSystem(bean.getSystem());
                errorList.add(errorInfo);
            }
        }
    }

}
