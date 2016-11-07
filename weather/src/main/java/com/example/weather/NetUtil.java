package com.example.weather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by zx on 16-10-18.
 */
public class NetUtil {
    public static final boolean hasNetWorkConection(Context ctx) {
        // 获取连接活动管理器
        final ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取连接的网络信息
        final NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isAvailable());
    }
}
