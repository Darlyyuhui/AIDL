package com.darly.snbc.adieas.ui.biz;

import android.os.RemoteException;

import com.darly.common.DLog;
import com.darly.common.retrofit.RxjavaRetrofitRequestUtil;
import com.darly.snbc.adieas.R;
import com.darly.snbc.adieas.SeverAidlCallBack;
import com.darly.snbc.adieas.base.BaseApplication;
import com.darly.snbc.adieas.bean.BaseInfo;
import com.darly.snbc.adieas.bean.InterfaceVersion;
import com.darly.snbc.adieas.bean.ParamerInfo;
import com.darly.snbc.adieas.bean.ResultInfo;
import com.darly.snbc.adieas.common.bolts.tasks.Task;
import com.darly.snbc.adieas.common.listener.BaseServerListener;
import com.darly.snbc.adieas.common.retrofit.HttpInterface;
import com.google.gson.JsonObject;
import com.snbc.bcvm.BCVMZK;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

    private final Executor SERIAL_EXECUTOR = Executors.newSingleThreadExecutor();


    @Override
    public BaseInfo paramerEmpty() {
        DLog.d(getClass().getSimpleName() + "paramerEmpty");
        BaseInfo info = new BaseInfo();
        info.setCode(-40031);
        info.setMsg(BaseApplication.getMessage(R.string.st_null_paramer));
        info.setHead(new InterfaceVersion());
        return info;
    }

    @Override
    public BaseInfo BVMInit(ParamerInfo paramer, SeverAidlCallBack callBack) {
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
    public BaseInfo BVMOpenPort(ParamerInfo paramer, SeverAidlCallBack callBack) {
        DLog.d(getClass().getSimpleName(), "BVMOpenPort");
        BaseInfo info = new BaseInfo();
        ResultInfo result = new ResultInfo();
        //打开端口
        int response = BCVMZK.Int_IniZKComm();
        if (response == 99) {
            info.setCode(response);
            info.setMsg("BVMOpenPort" + BaseApplication.getMessage(R.string.st_opt_success));
            info.setHead(new InterfaceVersion());
            return info;
        } else {
            info.setCode(response);
            info.setMsg("BVMOpenPort" + BaseApplication.getMessage(R.string.st_opt_failed));
            info.setHead(new InterfaceVersion());
            return info;
        }
    }

    @Override
    public BaseInfo BVMClosePort(ParamerInfo paramer, SeverAidlCallBack callBack) {
        DLog.d(getClass().getSimpleName(), "BVMClosePort");
        BaseInfo info = new BaseInfo();
        ResultInfo result = new ResultInfo();
        ArrayList<String> list = new ArrayList<>();
        if (paramer.getBoxid() != 0) {
            info.setCode(200);
            info.setMsg("BVMClosePort" + BaseApplication.getMessage(R.string.st_opt_success));
            info.setHead(new InterfaceVersion());
            list.add("BVMClosePort Test Message");
            result.setResult(list);
            info.setBody(result.toJson());
            return info;
        } else {
            info.setCode(120);
            info.setHead(new InterfaceVersion());
            info.setMsg("BVMClosePort" + BaseApplication.getMessage(R.string.st_opt_failed));
            return info;
        }
    }

    @Override
    public BaseInfo BVMGetRunningState(ParamerInfo paramer, SeverAidlCallBack callBack) {
        DLog.d(getClass().getSimpleName(), "BVMGetRunningState");
        BaseInfo info = new BaseInfo();
        ResultInfo result = new ResultInfo();
        ArrayList<String> list = new ArrayList<>();
        if (paramer.getBoxid() < 0) {
            info.setCode(-100);
            info.setMsg("参数传递错误，boxid不能小于零");
            return info;
        } else {
            int[] statebuf = new int[32];
            int[] devinfo = new int[256];
            devinfo[0] = paramer.getBoxid();
            devinfo[1] = 0;
            int response = BCVMZK.Int_GetstorHouseNum(devinfo, statebuf);
            if (response == 99) {
                info.setCode(response);
                info.setMsg("BVMGetRunningState" + BaseApplication.getMessage(R.string.st_opt_success));
                list.add(String.valueOf(statebuf[0]));
                result.setResult(list);
                info.setHead(new InterfaceVersion());
                info.setBody(result.toJson());
                return info;
            } else {
                info.setCode(response);
                info.setHead(new InterfaceVersion());
                info.setMsg("BVMGetRunningState" + BaseApplication.getMessage(R.string.st_opt_failed));
                return info;
            }
        }
    }

    @Override
    public BaseInfo BVMGetDoorState(ParamerInfo paramer, final SeverAidlCallBack callBack) {
        DLog.d(getClass().getSimpleName(), "BVMGetDoorState");
        BaseInfo info = new BaseInfo();
        ResultInfo result = new ResultInfo();
        ArrayList<String> list = new ArrayList<>();
        try {
            callBack.onInvokeStart();
            Task.call(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    Thread.sleep(5000);
                    DLog.d(getClass().getSimpleName(), "Thread.sleep(5000);");
                    BaseInfo info = new BaseInfo();
                    ResultInfo result = new ResultInfo();
                    ArrayList<String> list = new ArrayList<>();
                    if (new Random().nextBoolean()) {
                        info.setCode(200);
                        info.setMsg("BVMGetDoorState" + BaseApplication.getMessage(R.string.st_opt_success));
                        info.setHead(new InterfaceVersion());
                        list.add("BVMGetDoorState 测试信息");
                        result.setResult(list);
                        info.setBody(result.toJson());
                        callBack.onInvokeSuccess(info);
                    } else {
                        info.setCode(120);
                        info.setHead(new InterfaceVersion());
                        info.setMsg("BVMGetDoorState" + BaseApplication.getMessage(R.string.st_opt_failed));
                        callBack.onInvokeFailed(info);
                    }
                    return null;
                }
            }, SERIAL_EXECUTOR);
            info.setCode(200);
            info.setHead(new InterfaceVersion());
            info.setMsg("BVMGetDoorState" + BaseApplication.getMessage(R.string.st_opt_start));
            return info;
        } catch (RemoteException e) {
            info.setCode(110);
            info.setHead(new InterfaceVersion());
            info.setMsg("BVMGetDoorState" + BaseApplication.getMessage(R.string.st_opt_failed));
            return info;
        }
    }

    @Override
    public BaseInfo BVMGetFGFault(ParamerInfo paramer, SeverAidlCallBack callBack) {
        DLog.d(getClass().getSimpleName(), "BVMGetFGFault");
        BaseInfo info = new BaseInfo();
        ResultInfo result = new ResultInfo();
        ArrayList<String> list = new ArrayList<>();

        if (new Random().nextBoolean()) {
            info.setCode(200);
            info.setMsg("BVMGetFGFault" + BaseApplication.getMessage(R.string.st_opt_success));
            list.add("BVMGetFGFault 测试信息");
            result.setResult(list);
            info.setBody(result.toJson());
            info.setHead(new InterfaceVersion());
            return info;
        } else {
            info.setCode(120);
            info.setHead(new InterfaceVersion());
            info.setMsg("BVMGetFGFault" + BaseApplication.getMessage(R.string.st_opt_failed));
            return info;
        }
    }

    @Override
    public BaseInfo BVMCleanSysFault(ParamerInfo paramer, SeverAidlCallBack callBack) {
        DLog.d(getClass().getSimpleName(), "BVMCleanSysFault");
        BaseInfo info = new BaseInfo();
        ResultInfo result = new ResultInfo();
        ArrayList<String> list = new ArrayList<>();

        if (new Random().nextBoolean()) {
            info.setCode(200);
            info.setMsg("BVMCleanSysFault" + BaseApplication.getMessage(R.string.st_opt_success));
            list.add("BVMCleanSysFault 测试信息");
            result.setResult(list);
            info.setBody(result.toJson());
            info.setHead(new InterfaceVersion());
            return info;
        } else {
            info.setCode(120);
            info.setHead(new InterfaceVersion());
            info.setMsg("BVMCleanSysFault" + BaseApplication.getMessage(R.string.st_opt_failed));
            return info;
        }
    }

    @Override
    public BaseInfo BVMInitXYRoad(ParamerInfo paramer, SeverAidlCallBack callBack) {
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
            info.setHead(new InterfaceVersion());
            return info;
        } else {
            info.setCode(120);
            info.setHead(new InterfaceVersion());
            info.setMsg("BVMInitXYRoad" + BaseApplication.getMessage(R.string.st_opt_failed));
            return info;
        }
    }

}
