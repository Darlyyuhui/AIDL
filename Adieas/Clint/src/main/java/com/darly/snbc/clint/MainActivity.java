package com.darly.snbc.clint;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.darly.snbc.clint.common.Base64Utils;
import com.darly.snbc.clint.common.BinderUtils;
import com.darly.snbc.clint.common.RSAUtils;
import com.snbc.bvm.ServerAidlInterface;
import com.snbc.bvm.SeverAidlCallBack;
import com.snbc.bvm.bean.BaseInfo;
import com.snbc.bvm.bean.InParamer;
import com.snbc.bvm.bean.ParamerInfo;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    public static String Public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6b5H69Hg7WjYMh8MgEFtBi0p7Rlz6Hhs+dWPRYmudm8FXCp/wA6WEL7IlWsZSSPZwL+UhogChVWGniixmsNJ701paxCD5zH3TVOS5qGph/K3PHg3Q0KaaiAT5z4sgNRCoKrugDEWP9RYxx1NiSwaD1FA+4Aix+x2+OZdw6zzZ4QIDAQAB";

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

//        if (BinderUtils.checkPackInfo(this, "com.snbc.bvm")) {
//            if (BinderUtils.openPackage(this, "com.snbc.bvm")) {
        Intent intent = new Intent();
//                intent.setPackage("com.snbc.bvm");
        intent.setPackage("com.darly.snbc.adieas");
        intent.setAction("android.intent.action.ServerService");
        bindService(intent, this, Context.BIND_AUTO_CREATE);
//            } else {
//                Toast.makeText(this, "关联应用打开失败，请检查应用是否正常", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "关联应用没有安装，请下载安装", Toast.LENGTH_SHORT).show();
//        }
        id_main_tv = findViewById(R.id.id_main_tv);
        id_main_key = findViewById(R.id.id_main_key);
        id_main_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anInterface != null) {
                    try {
                        int key = anInterface.Init("KEy");
                        id_main_tv.setText(key + "");
//                        ParamerInfo paramerInfo = new ParamerInfo();
//                        paramerInfo.setAddr(12);
//                        paramerInfo.setBoxid(12);
//                        paramerInfo.setFilepath("3333333333333lafksdjflakjsdflkj");
//                        InParamer paramer = new InParamer();
//                        String id = UUID.randomUUID().toString();
//                        paramer.setRandom(id);
//                        byte[] byt = RSAUtils.encryptDataByPublic(paramerInfo.toJson().getBytes(),Public_key);
//                        paramer.setParamer(Base64Utils.encode(byt));
//                        paramer.setMethod(InParamer.MethodEnum.BVMINIT);
//                        BaseInfo info = anInterface.onBinder(paramer, null);
//                        if (info != null) {
//                            id_main_tv.setText(info.getCode() + info.getMsg());
//                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
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
                        byte[] byt = RSAUtils.encryptDataByPublic(paramerInfo.toJson().getBytes(), Public_key);
                        paramer.setParamer(Base64Utils.encode(byt));
                        paramer.setMethod(InParamer.MethodEnum.BVMCTRLSALEGOODSSTEPPRO);
                        BaseInfo info = anInterface.onBinder(paramer, null);
                        if (info != null) {

                            String kk = new String(RSAUtils.decryptDataByPublic(Base64Utils.decode(info.getBody()), Public_key));
                            id_main_tv.setText(info.getCode() + info.getMsg() + kk);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
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

                new AsyncTask<Void, Void, BaseInfo>() {
                    @Override
                    protected BaseInfo doInBackground(Void... voids) {
                        if (anInterface != null) {
                            try {
                                ParamerInfo paramerInfo = new ParamerInfo();
                                paramerInfo.setAddr(12);
                                paramerInfo.setBoxid(12);
                                InParamer paramer = new InParamer();
                                String id = UUID.randomUUID().toString();
                                paramer.setRandom(id);
                                paramer.setParamer(paramerInfo.toJson());
                                paramer.setMethod(InParamer.MethodEnum.BVMELECDOORCTRL);
                                BaseInfo info = anInterface.onBinder(paramer, new SeverAidlCallBack.Stub() {
                                    @Override
                                    public void onInvokeStart() throws RemoteException {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String tx = id_main_tv.getText().toString().trim();
                                                id_main_tv.setText(tx + "\r\nonInvokeStart");
                                            }
                                        });
                                    }

                                    @Override
                                    public void onInvokeSuccess(final BaseInfo info) throws RemoteException {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String tx = id_main_tv.getText().toString().trim();
                                                id_main_tv.setText(tx + "\r\nonInvokeSuccess" + info.getCode() + info.getMsg());
                                            }
                                        });
                                    }

                                    @Override
                                    public void onInvokeFailed(final BaseInfo info) throws RemoteException {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String tx = id_main_tv.getText().toString().trim();
                                                id_main_tv.setText(tx + "\r\nonInvokeFailed" + info.getCode() + info.getMsg());
                                            }
                                        });
                                    }
                                });
                                return info;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }else {
                            return null;
                        }
                    }

                    @Override
                    protected void onPostExecute(BaseInfo info) {
                        if (info != null) {
                            String tx = id_main_tv.getText().toString().trim();
                            id_main_tv.setText(tx + "\r\n" + info.getCode() + info.getMsg());
                        }
                    }
                }.execute();
            }
        });
        id_main_btn_aysn = findViewById(R.id.id_main_btn_aysn);
        id_main_btn_aysn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, BaseInfo>() {
                    @Override
                    protected BaseInfo doInBackground(Void... voids) {
                        if (anInterface != null) {
                            try {
                                ParamerInfo paramerInfo = new ParamerInfo();
                                paramerInfo.setAddr(12);
                                paramerInfo.setBoxid(12);
                                InParamer paramer = new InParamer();
                                String id = UUID.randomUUID().toString();
                                paramer.setRandom(id);
                                paramer.setParamer(paramerInfo.toJson());
                                paramer.setMethod(InParamer.MethodEnum.BVMSTARTSHIP);
                                BaseInfo info = anInterface.onBinder(paramer, new SeverAidlCallBack.Stub() {
                                    @Override
                                    public void onInvokeStart() throws RemoteException {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String tx = id_main_tv.getText().toString().trim();
                                                id_main_tv.setText(tx + "\r\nonInvokeStart");
                                            }
                                        });
                                    }

                                    @Override
                                    public void onInvokeSuccess(final BaseInfo info) throws RemoteException {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String tx = id_main_tv.getText().toString().trim();
                                                id_main_tv.setText(tx + "\r\nonInvokeSuccess" + info.getCode() + info.getMsg());
                                            }
                                        });
                                    }

                                    @Override
                                    public void onInvokeFailed(final BaseInfo info) throws RemoteException {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String tx = id_main_tv.getText().toString().trim();
                                                id_main_tv.setText(tx + "\r\nonInvokeFailed" + info.getCode() + info.getMsg());
                                            }
                                        });
                                    }
                                });
                                return info;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }else {
                            return null;
                        }
                    }

                    @Override
                    protected void onPostExecute(BaseInfo info) {
                        if (info != null) {
                            String tx = id_main_tv.getText().toString().trim();
                            id_main_tv.setText(tx + "\r\n" + info.getCode() + info.getMsg());
                        }
                    }
                }.execute();
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
