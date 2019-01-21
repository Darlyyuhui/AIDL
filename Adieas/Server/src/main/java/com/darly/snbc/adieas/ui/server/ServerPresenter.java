package com.darly.snbc.adieas.ui.server;

import android.text.TextUtils;

import com.darly.snbc.adieas.R;
import com.darly.snbc.adieas.SeverAidlCallBack;
import com.darly.snbc.adieas.base.BaseApplication;
import com.darly.snbc.adieas.bean.BaseInfo;
import com.darly.snbc.adieas.bean.InterfaceVersion;
import com.darly.snbc.adieas.bean.ParamerInfo;
import com.darly.snbc.adieas.common.listener.BaseServerListener;
import com.darly.snbc.adieas.ui.biz.ServerBiz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 服务拆分方法合集
 * 包名称：com.darly.snbc.adieas.ui.server
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/14 9:21
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class ServerPresenter {

    private BaseServerListener biz;

    private InterfaceVersion resultInfo;


    public ServerPresenter() {
        this.biz = new ServerBiz();
        this.resultInfo = new InterfaceVersion();
    }


    /**
     * 判断接口定义版本是否一致
     * @param id 接口id
     * @param version 接口版本
     * @return 验证结果
     */
    public boolean isCurrentVersion(String id,String version){
        if (TextUtils.isEmpty(id)||TextUtils.isEmpty(version)){
            return false;
        }else {
            if (id.equals(resultInfo.getId())&&version.equals(resultInfo.getVersion())) {
                return true;
            }else {
                return false;
            }
        }
    }

    /**
     * 错误的秘钥
     * @return 返回秘钥错误，
     */
    public BaseInfo errorKey() {
        return biz.paramerEmpty();
    }
    /**
     * 接口版本不一致，无法通讯
     * @return 返回接口版本错误
     */
    public BaseInfo errorVersion() {
        return biz.paramerEmpty();
    }

    /**
     * 参数为空
     * @return 返回空参错误
     */
    public BaseInfo emptyParam() {
        return biz.paramerEmpty();
    }




    /**
     * 异步接口调用
     *
     * @param desc
     * @param paramer 传入参数对象
     * @param callback 传入监听
     * @return 输出结果对象
     */
    public BaseInfo switchMethodCallBack(String methodName, ParamerInfo paramer, SeverAidlCallBack callback) {
        BaseInfo info = new BaseInfo();
        try {
            Class cls = biz.getClass();
            Method method = cls.getDeclaredMethod(methodName, ParamerInfo.class, SeverAidlCallBack.class);
            BaseInfo infos = (BaseInfo) method.invoke(cls.newInstance(), paramer, callback);
            return infos;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            info.setCode(-101);
            info.setMsg(BaseApplication.getMessage(R.string.st_nosuchmethodexception));
            return info;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            info.setCode(-102);
            info.setMsg(BaseApplication.getMessage(R.string.st_illegalaccessexception));
            return info;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            info.setCode(-103);
            info.setMsg(BaseApplication.getMessage(R.string.st_invocationtargetexception));
            return info;
        } catch (InstantiationException e) {
            e.printStackTrace();
            info.setCode(-104);
            info.setMsg(BaseApplication.getMessage(R.string.st_instantiationexception));
            return info;
        }
    }
}
