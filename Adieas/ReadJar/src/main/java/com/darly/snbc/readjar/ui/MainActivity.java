package com.darly.snbc.readjar.ui;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * 包名称：com.darly.snbc.ui
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/2/15 10:38
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class MainActivity extends AppCompatActivity implements OnClickListener,ScheduledTaskListener {

    String path = Environment.getExternalStorageDirectory().getPath();
    Button id_main_btn;
    Button id_main_start;
    Button id_main_stop;
    Button id_main_xy;
    ScheduledTask task =  new ScheduledTask();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_main_btn = findViewById(R.id.id_main_btn);
        id_main_start = findViewById(R.id.id_main_start);
        id_main_stop= findViewById(R.id.id_main_stop);
        id_main_btn.setOnClickListener(this);
        id_main_start.setOnClickListener(this);
        id_main_stop.setOnClickListener(this);
        id_main_xy= findViewById(R.id.id_main_xy);
        id_main_xy.setOnClickListener(this);

        task.setScheduledTaskListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_main_btn:
                findJar();
                break;
            case R.id.id_main_start:
                task.excuteSchedule();
                break;
            case R.id.id_main_stop:
                task.shutDown();
                break;
            case R.id.id_main_xy:
                task.shutDown();
                Log.i(getClass().getSimpleName(), "停止线程池，进行货到扫描");
                handler.sendEmptyMessageDelayed(1,5000);
                break;
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i(getClass().getSimpleName(), "货到扫描完成，开启线程池");
            task.excuteSchedule();
        }
    };

    private void findJar() {
        File file  = new File(path);
        if (file.exists()){
            File out = new File(path,"readjar/");
            if (!out.exists()){
                out.mkdir();
            }
            Log.i(getClass().getSimpleName(),out.getAbsolutePath());
            String jarPath = out.getPath()+"/"+out.list()[0];
            try {
                URLClassLoader loader = new URLClassLoader(new URL[]{out.toURL()},Thread.currentThread().getContextClassLoader());
                JarFile jarFile = new JarFile(jarPath);
                Enumeration enumeration = jarFile.entries();
                while (enumeration.hasMoreElements()){
                    JarEntry entry = (JarEntry) enumeration.nextElement();
                    String name = entry.getName();
                    if (name.endsWith(".class")){
                        String t = name.replace(".class","");
                        String re =  t.replace("/",".");
                        try {
                            Class cls = loader.loadClass(re);
                            Log.i(getClass().getSimpleName(), "本次浏览类："+re);
                            Method[] me = cls.getDeclaredMethods();
                            if (me != null && me.length > 0) {
                                for (Method m : me) {
                                    Log.i(getClass().getSimpleName(), "方法名称："+m);
                                }
                            } else {
                                Log.i(getClass().getSimpleName(), re + "没有定义方法");
                            }
                            Field[] fields = cls.getDeclaredFields();
                            if (fields != null && fields.length > 0) {
                                for (Field m : fields) {
                                    Log.i(getClass().getSimpleName(),"参数名称："+ m);
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


    @Override
    public void doorPoolTime(String name) {
        Log.i(getClass().getSimpleName(),name);
    }

    @Override
    public void workPoolTime(String name) {
        Log.i(getClass().getSimpleName(),name);
    }

    @Override
    public void heartPoolTime(String name) {
        Log.i(getClass().getSimpleName(),name);
    }

    @Override
    public void errorPoolTime(String name) {
        Log.i(getClass().getSimpleName(),name);
    }

    @Override
    public void goodsPoolTime(String name) {
        Log.i(getClass().getSimpleName(),name);
    }
}
