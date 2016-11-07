package com.example.weather;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.util.TimeUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zx on 16-10-18.
 */
//daka
public class OkhttpCache extends Application {
    private static final String TAG = "okhttpcache.....";
    private static OkhttpCache application;
    public static int OKHTTP_TAG=1;
    static     File file=new File("/data/data/com.example.weather/cache/","okhttpCache");
    static Cache cache=new Cache(file,1024*1024*10);

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

    }

    public static OkhttpCache getInstance() {
        return application;
    }
//    private static OkHttpClient which(int tag){
//        switch (tag){
//            case 1:
//                return REWRITE_CACHE_CONTROL_INTERCEPTOR;
//            case 2:
//                return REFRESHINTERCEPTOR;
//            default:
//                return null;
//        }
//    }

    public static OkHttpClient defaultOkHttpClient() {
        Log.i(TAG,"我是defaultokhttpClient");
        Log.i(TAG,"加了yigeintercepter");
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .connectTimeout(15 * 1000, TimeUnit.MILLISECONDS)
                .cache( cache)

        //设置拦截器
 //
                //           .addInterceptor(LoggingInterceptor)
    //        .addNetworkInterceptor(APPLICATIONINTERCEPTER)
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
        .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor(LoggingInterceptor)
                .build();
     //   Log.i(TAG,"结束");
        //client.addInterceptor(Logging
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        // Interceptor);
        return client;
    }

    public static OkHttpClient defaultRefreshOkhttp(){
        //File file=new File("/data/data/com.example.weather/cache/","okhttpCache");
        //Cache cache=new Cache(file,1024*1024*10);
        Log.i(TAG,"我是defaultRefreshOkhttp");
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .connectTimeout(15 * 1000, TimeUnit.MILLISECONDS)
                .cache( cache)
                //设置拦截器
                //
                //           .addInterceptor(LoggingInterceptor)
                //        .addNetworkInterceptor(APPLICATIONINTERCEPTER)
                .addInterceptor(REFRESHINTERCEPTOR)
                .addNetworkInterceptor(REFRESHINTERCEPTOR)
                .addInterceptor(LoggingInterceptor)
                .build();
        Log.i(TAG,"结束");
        //client.addInterceptor(LoggingInterceptor);
        return client;


    }
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        //方案一  有网没网都先读缓存
        Log.i(TAG,"我是日常调用的");
        Request request = chain.request();
        Log.i(TAG, "request:" + request);
        Response response = chain.proceed(request);
        Log.i(TAG, "response:" + response);
        Log.i(TAG, "!!!");
//            String cache=request.cacheControl().toString();
//            if (TextUtils.isEmpty(cache)){
//                cache="public,max-age=360,max-stale=360";
//            }
        // Log.i(TAG,"cache:"+cache);
        int maxAge=60*60;
        return response.newBuilder()
                .header("Cache-Control", "public,max-age="+maxAge)
                .removeHeader("Pragma")
                .build();
    }


};
    //intercerpter如何动态改变

//刷新时调用，有网请求数据并保存缓存失效时间为1小时，无网读取缓存
        private static final Interceptor REFRESHINTERCEPTOR =new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.i(TAG,"我是刷新调用的");
                boolean netWorkConection = NetUtil.hasNetWorkConection(OkhttpCache.getInstance());
                Request request=chain.request();

                //没网的话 ，读缓存
                if (!netWorkConection) {
                    request=request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    Log.i(TAG,"no network");
                }

                Response response=chain.proceed(request);
                //如果有网的话 ，设置response
                if (netWorkConection) {
                    int maxAge = 0;//设置网络缓存超时时间为0
                    Log.i(TAG, "has network maxAge=" + maxAge);
                    response.newBuilder()
                            .header("Cache-Control", "public,max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();


                }else {
                    Log.i(TAG,"netWork error");
                    int maxStale=60;
                    Log.i(TAG,"has maxstale="+maxStale);
                    response.newBuilder()
                            .header("Cache-Control","public,only-if cached,max-stale="+maxStale)
                            .removeHeader("Pragma")
                            .build();
                    Log.i(TAG,"response build maxStale="+maxStale);
                }
                Log.i(TAG,"我调用了 network拦截器");

                return response;
            }
        };
            //方案二：无网读缓存，有网根据过期时间重新请求
    private static final Interceptor APPLICATIONINTERCEPTER =new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request();
            if (NetUtil.hasNetWorkConection(getInstance())){
                int maxAge=60*60;
                request=request.newBuilder()
                        .header("Cache-Control","public,only-if-cached,max-age="+maxAge)
                        .removeHeader("Pragma")
                        .build();

            }else {
                 request=request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();

            }
            Response response=chain.proceed(request);

            return response;
        }
    };

    private static final Interceptor LoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request=chain.request();

            long t1 = System.nanoTime();
            Log.i(TAG,String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.i(TAG,String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    };

}

