package com.dk.searchexpress.utils;

import android.util.Log;

/**
 * @ClassName: LogUtil
 * @Author: dongke
 * @Date: 2020/4/10 20:35
 * @Description:
 */
public class LogUtil {
    private static final String TAG = "Express";

    public static void loge(String msg) {
        Log.e(TAG, msg);
    }

    public static void logi(String msg) {
        Log.i(TAG, msg);
    }

    public static void logw(String msg) {
        Log.w(TAG, msg);
    }
}
