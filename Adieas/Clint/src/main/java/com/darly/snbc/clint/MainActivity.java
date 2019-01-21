package com.darly.snbc.clint;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.darly.snbc.adieas.ServerAidlInterface;
import com.darly.snbc.adieas.SeverAidlCallBack;
import com.darly.snbc.adieas.bean.BaseInfo;
import com.darly.snbc.adieas.bean.InParamer;
import com.darly.snbc.adieas.bean.ParamerInfo;
import com.darly.snbc.clint.common.BinderUtils;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    private ServerAidlInterface anInterface;
    private Button id_main_key;
    private Button id_main_btn;
    private Button id_main_btn_null;
    private Button id_main_btn_wrong;
    private Button id_main_btn_aysn;

    private TextView id_main_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BinderUtils.checkPackInfo(this, "com.darly.snbc.adieas")) {
            if (BinderUtils.openPackage(this, "com.darly.snbc.adieas")) {
                Intent intent = new Intent();
                intent.setClassName("com.darly.snbc.adieas", "com.darly.snbc.adieas.ui.server.ServerService");
                bindService(intent, this, Context.BIND_AUTO_CREATE);
            } else {
                Toast.makeText(this, "关联应用打开失败，请检查应用是否正常", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "关联应用没有安装，请下载安装", Toast.LENGTH_SHORT).show();
        }
        id_main_tv = findViewById(R.id.id_main_tv);
        id_main_key = findViewById(R.id.id_main_key);
        id_main_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anInterface != null) {
                    try {
                        ParamerInfo paramerInfo = new ParamerInfo();
                        paramerInfo.setAddr(12);
                        paramerInfo.setBoxid(12);
                        paramerInfo.setFilepath("3333333333333lafksdjflakjsdflkj");
                        InParamer paramer = new InParamer();
                        String id = UUID.randomUUID().toString();
                        paramer.setRandom(id);
                        paramer.setParamer(paramerInfo.toJson() + id);
                        paramer.setMethod(InParamer.MethodEnum.BVMINIT);
                        BaseInfo info = anInterface.onBinder(paramer, null);
                        if (info != null) {
                            id_main_tv.setText(info.getCode() + info.getMsg());
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        id_main_btn = findViewById(R.id.id_main_btn);
        id_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anInterface != null) {
                    try {
                        ParamerInfo paramerInfo = new ParamerInfo();
                        paramerInfo.setAddr(12);
                        paramerInfo.setBoxid(12);
                        paramerInfo.setFilepath("3333333333333lafksdjflakjsdflkj");
                        InParamer paramer = new InParamer();
                        String id = UUID.randomUUID().toString();
                        paramer.setRandom(id);
                        paramer.setParamer(paramerInfo.toJson() + id);
                        paramer.setMethod(InParamer.MethodEnum.BVMINIT);
                        BaseInfo info = anInterface.onBinder(paramer, null);
                        if (info != null) {
                            id_main_tv.setText(info.getCode() + info.getMsg());
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        id_main_btn_null = findViewById(R.id.id_main_btn_null);
        id_main_btn_null.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anInterface != null) {
                    try {
                        BaseInfo info = anInterface.onBinder(null, null);
                        Log.d(getClass().getSimpleName(), "Clint调用成功");
                        if (info != null) {
                            id_main_tv.setText(info.getCode() + info.getMsg());
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        id_main_btn_wrong = findViewById(R.id.id_main_btn_wrong);
        id_main_btn_wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anInterface != null) {
                    try {
                        ParamerInfo paramerInfo = new ParamerInfo();
                        paramerInfo.setAddr(12);
                        paramerInfo.setBoxid(12);
                        paramerInfo.setFilepath("3333333333333lafksdjflakjsdflkj");
                        InParamer paramer = new InParamer();
                        String id = UUID.randomUUID().toString();
                        paramer.setRandom(id);
                        paramer.setParamer(paramerInfo.toJson() + id);
                        paramer.setMethod(InParamer.MethodEnum.BVMGETRUNNINGSTATE);
                        BaseInfo info = anInterface.onBinder(paramer, null);
                        Log.d(getClass().getSimpleName(), "Clint调用成功");
                        if (info != null) {
                            id_main_tv.setText(info.getCode() + info.getMsg());
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        id_main_btn_aysn = findViewById(R.id.id_main_btn_aysn);
        id_main_btn_aysn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anInterface != null) {
                    try {
                        ParamerInfo paramerInfo = new ParamerInfo();
                        paramerInfo.setAddr(12);
                        paramerInfo.setBoxid(12);
                        paramerInfo.setFilepath("测试数据");
                        InParamer paramer = new InParamer();
                        String id = UUID.randomUUID().toString();
                        paramer.setRandom(id);
                        paramer.setParamer(id);
                        paramer.setMethod(InParamer.MethodEnum.BVMGETDOORSTATE);
                        BaseInfo info = anInterface.onBinder(paramer, new SeverAidlCallBack.Stub() {
                            @Override
                            public void onInvokeStart() throws RemoteException {
                                Log.i(getClass().getSimpleName(), "onInvokeStart");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        id_main_tv.setText("onInvokeStart");
                                    }
                                });
                            }

                            @Override
                            public void onInvokeSuccess(final BaseInfo info) throws RemoteException {
                                Log.i(getClass().getSimpleName(), "onInvokeSuccess");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        id_main_tv.setText("onInvokeSuccess" + info.getCode() + info.getMsg());
                                    }
                                });
                            }

                            @Override
                            public void onInvokeFailed(final BaseInfo info) throws RemoteException {
                                Log.i(getClass().getSimpleName(), "onInvokeFailed");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        id_main_tv.setText("onInvokeFailed" + info.getCode() + info.getMsg());
                                    }
                                });
                            }
                        });
                        Log.d(getClass().getSimpleName(), "Clint调用成功");
                        if (info != null) {
                            id_main_tv.setText(info.getCode() + info.getMsg());
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        anInterface = ServerAidlInterface.Stub.asInterface(service);
        Log.d(getClass().getSimpleName(), "Clint实例化完成");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
