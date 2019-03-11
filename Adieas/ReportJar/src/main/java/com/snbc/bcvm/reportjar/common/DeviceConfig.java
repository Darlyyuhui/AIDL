package com.snbc.bcvm.reportjar.common;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


/**
 * 配置文件日志文件类
 * Created by 王峰 on 2017/7/13.
 */

public class DeviceConfig {
    protected static final String strDeviceConfigini = "snbcconfig1.properties";
    protected static final String strDevicePath = "/mnt/sdcard/Android/data/bvm/set/";

    protected static final String strLogPath = "/mnt/sdcard/Android/data/bvm/";
    //protected static final String strDeviceUpdatePath = "/mnt/sdcard/Android/data/bvm/set/update/";
    public static final String strDeviceconfig = FSDeviceConfig.FSDevConfig;

    public static final String PORTNUM = "PORTNUM";
    public static final String SCANDEV = "SCANDEV";
    public static final String PORTNAME = "PORTNAME";

    public static Properties properties = new Properties();

    public static boolean IsExistFile(String strPath){
        File mfile = new File(strPath);
        return mfile.exists();
    }

    //log
    public static String GetDir(){
        return strLogPath ;
    }
  /*  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String GetCommport(String MainOrSub) throws IOException {
        if (MainOrSub.equals("GetMainPort")){
            String AllConfig = ReadDeviceConfig();
            if (!AllConfig.equals("")){
                String[] AllConfigArray = AllConfig.split("=");
                String AllConfig1 = AllConfigArray[1];
                String commportResult = AllConfig1.substring(0,12);
                System.out.println(commportResult);
                return commportResult;
            }
        }else if(MainOrSub.equals("GetSubPort")){
            String AllConfig = ReadDeviceConfig();
            if (!AllConfig.equals("")){
                String[] AllConfigArray = AllConfig.split("=");
                String AllConfig1 = AllConfigArray[3];
                String commportResult = AllConfig1.substring(0,12);
                System.out.println(commportResult);
                return commportResult;
            }
        }

        return "";
    }*/
  /*  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static int GetBaudrate(String MainOrSub) throws IOException {
        String AllConfig = ReadDeviceConfig();
        if (MainOrSub.equals("GetMainBaudrate")){
            if (!AllConfig.equals("")){
                String[] AllConfigArray = AllConfig.split("=");
                String AllConfig2 = AllConfigArray[2].substring(0,6);
                int value = Integer.parseInt(AllConfig2);
                System.out.println(AllConfig2);
                return value;
            }
        }else if(MainOrSub.equals("GetSubBaudrate")){
            if (!AllConfig.equals("")){
                String[] AllConfigArray = AllConfig.split("=");
                String AllConfig2 = AllConfigArray[4];
                int value = Integer.parseInt(AllConfig2);
                System.out.println(AllConfig2);
                return value;
            }
        }

        return -1;
    }*/
    public static String GetConfigIni(){
        return
                strDevicePath+strDeviceConfigini;
    }


    public static boolean CreateNewPath(String path){
        try{
            if (path == null || path.trim().equals("")){
                return false;
            }

            if (path.contains("/")){
                String dirs = path.substring(0,path.lastIndexOf("/"));
                File dir = new File(dirs);
                if (!dir.exists()){
                    dir.mkdirs();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean CreateNewPathFile(String path){
        try{
            if (path == null || path.trim().equals("")){
                return false;
            }

            if (path.contains("/")){
                String dirs = path.substring(0,path.lastIndexOf("/"));
                File dir = new File(dirs);
                if (!dir.exists()){

                    dir.mkdirs();
                        }

                    Log.e("DeviceConfig","makedir");
                }
                File file = new File(path);
                if (!file.exists()){
                    try {
                        file.createNewFile();
                        return true;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

        }catch (Exception e){
            e.printStackTrace();

        }
        return false;
    }

    public static byte[] GetDeviceConfigByte(){
        return strDeviceconfig.getBytes();
    }
    /**
     * 初始化数据
     * */

    public static boolean InitDeviceConfig() throws IOException {
        if (IsExistFile(GetConfigIni())){
            File file = new File(GetConfigIni());
            FileOutputStream mFileOutputStream = new FileOutputStream(file);
            mFileOutputStream.write(GetDeviceConfigByte());
            mFileOutputStream.close();
        }else {
            return false;
        }
        return true;
    }

    /**
     * 读取文件数据
     * */

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String ReadDeviceConfig() throws IOException {
        StringBuilder result = new StringBuilder();
        InputStream inputStream = new FileInputStream(new File(DeviceConfig.GetConfigIni()));
        BufferedReader br = new BufferedReader(new FileReader(new File(DeviceConfig.GetConfigIni())));
        String s = null;
        while ((s = br.readLine())!= null){
            result.append(System.lineSeparator() + s);
        }
        br.close();

        return result.toString();
    }
    /**
     * 获取配置文件中的设备参数
     *autr 王峰
     */
    public static String GetProperty(String strTag){
        String strRst = null;
        if (properties != null){
            try {
                InputStream in = new BufferedInputStream(new FileInputStream(GetConfigIni()));
                properties.load(in);
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            strRst = properties.getProperty(strTag);
        }
        return strRst == null? null : strRst.trim();
    }

    public static boolean setGetProperty(String key,String value){
        if (properties != null){
            try {
                InputStream in = new BufferedInputStream(new FileInputStream(GetConfigIni()));
                properties.load(in);
                properties.setProperty(key,value);
                in.close();
                OutputStream fos = new FileOutputStream(strDevicePath + strDeviceConfigini);
                properties.store(fos, null);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean setDevtype(String value){
        return setGetProperty(SCANDEV,value);
    }

    public static boolean setPortname(String value){
        return setGetProperty(PORTNAME,value);
    }

    public static boolean setPortnum(String value){
        return setGetProperty(PORTNUM,value);
    }
}
