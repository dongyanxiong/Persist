package com.example.daxiong.persist.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2016/7/26.
 */
public class AppUtil {
    private AppUtil(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    //get app name
    public static String getAppName(Context context){

        PackageManager packageManager = context.getPackageManager();
        try{
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        }catch(PackageManager.NameNotFoundException e){
            e.getMessage();
        }
        return null;
    }


}
