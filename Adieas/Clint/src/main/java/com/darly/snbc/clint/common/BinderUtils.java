package com.darly.snbc.clint.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 判断关联项目是否正常
 * 包名称：com.darly.snbc.clint.common
 * 作者：zhangyuhui 项目名称：Adieas
 * 日期：2019/1/14 16:22
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：zhangyuhui@newbeiyang.com
 */
public class BinderUtils {
    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    public static boolean checkPackInfo(Context context, String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }


//    private static Intent getAppOpenIntentByPackageName(Context context, String packageName) {
//        //Activity完整名
//        String mainAct = null;
//        //根据包名寻找
//        PackageManager pkgMag = context.getPackageManager();
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        List<ResolveInfo> list = pkgMag.queryIntentActivities(intent,
//                PackageManager.GET_ACTIVITIES);
//        for (int i = 0; i < list.size(); i++) {
//            ResolveInfo info = list.get(i);
//            if (info.activityInfo.packageName.equals(packageName)) {
//                mainAct = info.activityInfo.name;
//                break;
//            }
//        }
//        if (TextUtils.isEmpty(mainAct)) {
//            return null;
//        }
//        intent.setComponent(new ComponentName(packageName, mainAct));
//        return intent;
//    }

    private static Context getPackageContext(Context context, String packageName) {
        Context pkgContext = null;
        if (context.getPackageName().equals(packageName)) {
            pkgContext = context;
        } else {
            // 创建第三方应用的上下文环境
            try {
                pkgContext = context.createPackageContext(packageName,
                        Context.CONTEXT_IGNORE_SECURITY
                                | Context.CONTEXT_INCLUDE_CODE);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return pkgContext;
    }

    public static boolean openPackage(Context context, String packageName) {
        Context pkgContext = getPackageContext(context, packageName);
//        Intent intent = getAppOpenIntentByPackageName(context, packageName);
        if (pkgContext != null ) {
//            && intent != null
//            pkgContext.startActivity(intent);
            return true;
        }
        return false;
    }

}
