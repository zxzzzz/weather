package com.example.weather;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zx on 16-10-11.
 */
public interface UrlQuery {

        //https://api.heweather.com/x3/weather?cityid=CN101010100&key=d2521b48a0774171a8b8defafac3c86ea
  //  @Headers("Cache-Control:public,max-age=360")
    @GET("x3/weather")
        //public Observable<WeatherBean> getKey(@Query("cityid")  String cityid,@Query("key") String key);
    public Observable<WeatherBean> getKey(@Query("cityid") String cityid, @Query("key") String key);
}
