package com.darly.snbc.readjar.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.darly.snbc.jarlib.bean.BaseResponse;
import com.darly.snbc.jarlib.common.JarlibListener;
import com.darly.snbc.jarlib.common.observer.JarLibInitCfg;
import com.darly.snbc.jarlib.controller.AirController;
import com.darly.snbc.readjar.BuildConfig;
import com.darly.snbc.readjar.R;
import com.darly.snbc.readjar.common.ScheduledTask;
import com.darly.snbc.readjar.common.ScheduledTaskListener;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;


/**
 * 包名称：com.darly.snbc.ui
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/2/15 10:38
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class MainActivity extends AppCompatActivity implements OnClickListener, ScheduledTaskListener {

    String path = Environment.getExternalStorageDirectory().getPath();
    Button id_main_btn;
    Button id_main_start;
    Button id_main_pause;
    Button id_main_xy;


    //门广播
    private int[] orsenState;//柜门状态
    private int orsenZState = -1;//闸门状态
    /*无状态变化时广播间隔时间*/
    private long checkDoorTimes = 0;//检查柜门状态次数，用来记时柜门60秒发一次广播
    private long checkZDoorTimes = 0;//检查闸门状态次数，用来记时闸门60秒发一次广播
    private long checkNoErrorTimes = 0;//检查无故障次数，用来记时无故障60秒发一次广播
    /*无状态变化时广播间隔时间*/
    private int NOSTATEDOORTIME = 60 * 1000;//柜门在无状态变化的情况，发送广播间隔时间，60秒
    private int NOSTATEZDOORTIME = 60 * 1000;//闸门在无状态变化的情况，发送广播间隔时间，60秒
    private int NOSTATEERRORTIME = 60 * 1000;//无故障状态变化的情况，发送广播间隔时间，60秒
    //整机状态
    private int[] orfgWorkState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_main_btn = findViewById(R.id.id_main_btn);
        id_main_start = findViewById(R.id.id_main_start);
        id_main_pause = findViewById(R.id.id_main_pause);
        id_main_btn.setOnClickListener(this);
        id_main_start.setOnClickListener(this);
        id_main_pause.setOnClickListener(this);
        id_main_xy = findViewById(R.id.id_main_xy);
        id_main_xy.setOnClickListener(this);
        ScheduledTask.getInstance().setScheduledTaskListener(this);


        orsenState = new int[]{-1};
        orfgWorkState = new int[]{-1};
    }

    int i = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_main_btn:
//                findJar();
                i++;
                showToast("調用內容111111111111111111111111111111111111111111\r\n111111111111111111111111111111111111111111111\r\n1111111111111111111----" + i, 6000);
//                //启动测试调用。
//                JarLibInitCfg.getInstance().initConfig(BuildConfig.DEBUG, this);
//                int key = AirController.getDefault(001);
//                Log.i(getClass().getSimpleName(), "接口調用返回" + key);
//
//                AirController.getDefault(001, new JarlibListener<BaseResponse>() {
//                    @Override
//                    public void onStart() {
//                        Log.i(getClass().getSimpleName(), "異步啟動接口調用返回");
//                    }
//
//                    @Override
//                    public void onSuccess(BaseResponse baseResponse) {
//                        Log.i(getClass().getSimpleName(), "異步啟動接口調用返回" + baseResponse.getCode() + baseResponse.getMsg());
//                    }
//
//                    @Override
//                    public void onFailed(BaseResponse baseResponse) {
//                        Log.i(getClass().getSimpleName(), "異步啟動接口調用返回" + baseResponse.getCode() + baseResponse.getMsg());
//                    }
//                });
                break;
            case R.id.id_main_start:
                CustomStylesToast();
//                ScheduledTask.getInstance().excuteSchedule();
                break;
            case R.id.id_main_pause:
                ScheduledTask.getInstance().onPauseTask();
                break;
            case R.id.id_main_xy:
                ScheduledTask.getInstance().onPauseTask();
                Log.i(getClass().getSimpleName(), "轮询任务暂停，进行货到扫描");
                handler.sendEmptyMessageDelayed(1, 10000);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(getClass().getSimpleName(), "进程调用了关闭");
        ScheduledTask.getInstance().shutDown();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i(getClass().getSimpleName(), "货到扫描完成，轮询任务重新开始");
            ScheduledTask.getInstance().onReStartTask();
        }
    };

    private void findJar() {

        try {
            PathClassLoader classLoader = (PathClassLoader) Thread
                    .currentThread().getContextClassLoader();
            String pk = JSON.class.getPackage().getName();
            DexFile dexFile = new DexFile(this.getPackageResourcePath());
            Enumeration<String> enumeration = dexFile.entries();
            while (enumeration.hasMoreElements()) {
                String entryName = enumeration.nextElement();
                if (entryName.contains(pk)) {
                    Class<?> entryClass = Class.forName(entryName, true, classLoader);//疑问：Class.forName(entryName);这种方式不知道为什么返回null，哪位大神知道原因，请指点一下小弟吧  感激不尽
                    Log.i(getClass().getSimpleName(), entryClass.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        File file = new File(path);
        if (!file.exists()) {
            File out = new File(path, "readjar/");
            if (!out.exists()) {
                out.mkdir();
            }
            Log.i(getClass().getSimpleName(), out.getAbsolutePath());
            String jarPath = out.getPath() + "/" + out.list()[0];
            try {
                URLClassLoader loader = new URLClassLoader(new URL[]{out.toURL()}, Thread.currentThread().getContextClassLoader());
                JarFile jarFile = new JarFile(jarPath);
                Enumeration enumeration = jarFile.entries();
                while (enumeration.hasMoreElements()) {
                    JarEntry entry = (JarEntry) enumeration.nextElement();
                    String name = entry.getName();
                    if (name.endsWith(".class")) {
                        String t = name.replace(".class", "");
                        String re = t.replace("/", ".");
                        try {
                            Class cls = loader.loadClass(re);
                            Log.i(getClass().getSimpleName(), "本次浏览类：" + re);
                            Method[] me = cls.getDeclaredMethods();
                            if (me != null && me.length > 0) {
                                for (Method m : me) {
                                    Log.i(getClass().getSimpleName(), "方法名称：" + m);
                                }
                            } else {
                                Log.i(getClass().getSimpleName(), re + "没有定义方法");
                            }
                            Field[] fields = cls.getDeclaredFields();
                            if (fields != null && fields.length > 0) {
                                for (Field m : fields) {
                                    Log.i(getClass().getSimpleName(), "参数名称：" + m);
                                }
                            } else {
                                Log.i(getClass().getSimpleName(), re + "没有定义参数");
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private Intent intent = new Intent();

    @Override
    public void onPauseScheduledTask(String msg) {
        Log.i(getClass().getSimpleName(), msg);
    }

    @Override
    public void onReStartScheduledTask(String msg) {
        Log.i(getClass().getSimpleName(), msg);
    }

    @Override
    public void doorPoolTime(int boxIndex, int senStateTmp, int senZState, String assetCode, String vemidValue, int boxIDState) {
        if (senStateTmp != orsenState[boxIndex]) {
            //状态发生变化后发送广播
            orsenState[boxIndex] = senStateTmp;
            checkDoorTimes = System.currentTimeMillis();//计数清零
            intent.setAction("com.snbc.bvm.action.senreceiver");
            intent.putExtra("SENSTATE", senStateTmp);
            intent.putExtra("host", assetCode);
            intent.putExtra("ghost", vemidValue);
            intent.putExtra("boxid", boxIDState + "");
            intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
            sendBroadcast(intent);
            Log.i(getClass().getSimpleName(), "售货机操作的柜机:" + boxIndex + assetCode + vemidValue + boxIDState + "-柜门发送广播----------------------");
        } else if (System.currentTimeMillis() - checkDoorTimes >= NOSTATEDOORTIME) {
            //记时超过60秒后发送广播
            checkDoorTimes = System.currentTimeMillis();//计数清零
            Log.i(getClass().getSimpleName(), "售货机操作的柜机:" + boxIndex + assetCode + vemidValue + boxIDState + "-柜门超过60s状态无变化发送广播----------------------");
        }
        if (senZState != orsenZState) {
            orsenZState = senZState;
            checkZDoorTimes = System.currentTimeMillis();//计数清零
            Log.i(getClass().getSimpleName(), "售货机操作的柜机:" + boxIndex + assetCode + vemidValue + boxIDState + "-闸机发送广播----------------------");
        } else if (System.currentTimeMillis() - checkZDoorTimes >= NOSTATEZDOORTIME) {
            orsenZState = senZState;
            checkZDoorTimes = System.currentTimeMillis();//计数清零
            Log.i(getClass().getSimpleName(), "售货机操作的柜机:" + boxIndex + assetCode + vemidValue + boxIDState + "-闸机超过60s状态无变化发送广播----------------------");
        }
    }

    @Override
    public void workPoolTime(int boxIndex, int fgWorkState, String assetCode, String vemidValue, int boxIDState) {
        if (fgWorkState != orfgWorkState[boxIndex]) {
            orfgWorkState[boxIndex] = fgWorkState;
            Log.i(getClass().getSimpleName(), "售货机操作的柜机:" + boxIndex + assetCode + vemidValue + boxIDState + "-整机状态发送广播----------------------");
        }
    }


    @Override
    public void errorPoolTime(int boxIndex, byte[] faultClean, byte[] faultReport, String assetCode, String vemidValue, int boxIDState) {
        if (faultReport != null && faultReport[0] <= 0) {
            if (System.currentTimeMillis() - checkNoErrorTimes >= NOSTATEERRORTIME) {//60秒无故障上报
                checkNoErrorTimes = System.currentTimeMillis();//故障上报，清零无故障上报计数
                Log.i(getClass().getSimpleName(), "售货机操作的柜机:" + boxIndex + assetCode + vemidValue + boxIDState + "-故障状态超过60s状态无变化发送广播----------------------");
            } else {
                return;
            }
        }
        checkNoErrorTimes = System.currentTimeMillis();//故障上报，清零无故障上报计数
        if (faultReport != null && faultReport[0] != 0) {
            Log.i(getClass().getSimpleName(), "售货机操作的柜机:" + boxIndex + assetCode + vemidValue + boxIDState + "-故障状态发送广播----------------------");
        }
    }


    @Override
    public void heartPoolTime(int boxIndex, int eheartcode, int zheartcode, String assetCode, String vemidValue, int boxIDState) {
        Log.i(getClass().getSimpleName(), "售货机操作的柜机:" + boxIndex + assetCode + vemidValue + boxIDState + "-心跳状态发送广播----------------------");
    }

    @Override
    public void goodsPoolTime(int boxIndex, List<Integer> goodsstate, String assetCode, String vemidValue, int boxIDState) {
        Log.i(getClass().getSimpleName(), "售货机操作的柜机:" + boxIndex + assetCode + vemidValue + boxIDState + "-货道状态发送广播----------------------");
    }

    @Override
    public void onStopScheduledTask(String msg) {
        Log.i(getClass().getSimpleName(), msg);
    }


    private Toast toast;


    private void CustomStylesToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_style,
                (ViewGroup) findViewById(R.id.ll_Toast));
        ImageView image = (ImageView) layout.findViewById(R.id.iv_ImageToast);
        image.setImageResource(R.mipmap.ic_launcher);
        TextView text = (TextView) layout.findViewById(R.id.tv_TextToast);
        text.setText("自定义Toast的样式");
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.LEFT | Gravity.TOP, 20, 50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    private long during = 0;
    private Timer timer = null;

    private void showToast(String msg, int time) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(msg);
        }
        long du = System.currentTimeMillis();
        if (du - during >= time) {
            during = du;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.show();
                }
            }, 0, 3000);// 3000表示点击按钮之后，Toast延迟3000ms后显示
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.cancel();
                    timer.cancel();
                }
            }, time);// 5000表示Toast显示时间为5秒
        } else {
            //Toast存在
            timer.cancel();

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.show();
                }
            }, 0, 3000);// 3000表示点击按钮之后，Toast延迟3000ms后显示
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.cancel();
                    timer.cancel();
                }
            }, du - during+time);// 5000表示Toast显示时间为5秒
        }
    }
}
