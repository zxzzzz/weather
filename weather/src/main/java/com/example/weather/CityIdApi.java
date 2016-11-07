package com.example.weather;



import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
/**
 * Created by zx on 16-10-16.
 */
//拼接 获取城市id的url
    //https://api.heweather.com/x3/citylist?search=allchina&key=d2521b48a0774171a8b8defafac3c86e
public interface CityIdApi {
    @GET("x3/citylist")
    public  Observable<CityIdBean> getCityId(@Query("search") String search,@Query("key") String key);
}
