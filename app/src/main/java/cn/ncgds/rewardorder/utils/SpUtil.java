package cn.ncgds.rewardorder.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/2/18 0018.
 */

public class SpUtil {
    public static final String PREF_NAME = "config";
    public static boolean getBoolean(Context ctx ,String key, boolean defaultValue){
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        return sp.getBoolean(key,defaultValue);
    }
    public static void setBoolean(Context ctx ,String key, boolean value){
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static String getString(Context ctx ,String key, String defaultValue){
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        return sp.getString(key,defaultValue);
    }
    public static void setString(Context ctx ,String key, String value){
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }
}
