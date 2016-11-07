package com.example.weather;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.util.Log;
import android.util.MutableShort;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zx on 16-10-11.
 */
//genju chuanru de cityId fanhui the weather info
public class RetrofitManager {
   static String baseUrl="https://api.heweather.com/";
    final static String RETROFIT_LOG="RETROFITMANAGER";
    public  String id;
    ShowWeatherFragment fragment;
    String cityName;
    String proveName;
    String cityid;
    String path="/data/data/com.example.weather/databases/city.db";
    SQLiteDatabase sqlietDatabase=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);

    DbManager dbManager=new DbManager(sqlietDatabase);

    //  static int TAG=1;

    //SQLiteDatabase sqLiteDatabase=SQLiteDatabase.openDatabase("/data/data/com.example.weather/")
    //获取城市的id
    //这个异步加载问题......

    public RetrofitManager(ShowWeatherFragment fragment) {
        this.fragment = fragment;
    }
    public void queryBundle(ShowWeatherFragment fragment){
        cityid=fragment.getArguments().getString("cityid");
        cityName=fragment.getArguments().getString("city");
        proveName=fragment.getArguments().getString("prove");
        Log.i(RETROFIT_LOG,"city:"+cityName);
        Log.i(RETROFIT_LOG,"prove:"+proveName);
        Log.i(RETROFIT_LOG,"id:"+cityid);

    };
    //将城市 /省份写到数据库里
    public static  void insertCityInfoToTable(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
            .client(OkhttpCache.defaultOkHttpClient())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                .build();
        CityIdApi cityIdApi=retrofit.create(CityIdApi.class);
        cityIdApi.getCityId("allchina","d2521b48a0774171a8b8defafac3c86e")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CityIdBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i(RETROFIT_LOG,"completed...cityid");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(RETROFIT_LOG,"error...cityid");

                    }

                    @Override
                    public void onNext(CityIdBean cityIdBean) {
                        SQLiteDatabase sqLiteDatabase=SQLiteDatabase.openDatabase("/data/data/com.example.weather/databases/city.db",null,SQLiteDatabase.OPEN_READWRITE);
                        DbManager dbManager=new DbManager(sqLiteDatabase);
                        Log.i(RETROFIT_LOG,"start...cityid");
                        int i=0;
                        ArrayList<CityIdBean.CityInfoBean> cityIdBeanArrayList=(ArrayList<CityIdBean.CityInfoBean>) cityIdBean.getCity_info();
                        for (CityIdBean.CityInfoBean c:cityIdBeanArrayList) {
                                    String ci=c.getCity();
                                    String d=c.getId();
                                    String pr=c.getProv();
                                    dbManager.insertAllCityInfo(d,ci,pr);

                                }
                            }

                });


    }
    private OkHttpClient whichClient(int tag){
        switch (tag){
            case 1:
                return OkhttpCache.defaultOkHttpClient();
            case 2:
                return OkhttpCache.defaultRefreshOkhttp();
            default:
                return OkhttpCache.defaultOkHttpClient();
        }
    }

//    public void queryCityIds(final int tag){
//        queryBundle(fragment);
//
//        Log.i(RETROFIT_LOG,"queryCityIds");
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .client(OkhttpCache.defaultOkHttpClient())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        CityIdApi cityIdApi=retrofit.create(CityIdApi.class);
//        cityIdApi.getCityId("allchina","d2521b48a0774171a8b8defafac3c86e")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<CityIdBean>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i(RETROFIT_LOG,"completed...cityid");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i(RETROFIT_LOG,"error...city");
////
//                    }
//
//                    @Override
//                    public void onNext(CityIdBean cityIdBean) {
//                        Log.i(RETROFIT_LOG,"start...cityid");
//                        int i=0;
//                        ArrayList<CityIdBean.CityInfoBean> cityIdBeanArrayList=(ArrayList<CityIdBean.CityInfoBean>) cityIdBean.getCity_info();
//                        for (CityIdBean.CityInfoBean c:cityIdBeanArrayList)
//                        {
//                            Log.i(RETROFIT_LOG,"citybean"+i++);
//                            String pro=c.getProv();
//                            Log.i(RETROFIT_LOG,"prov:"+pro);
//                            String city=c.getCity();
//                            Log.i(RETROFIT_LOG,"city:"+city);
//                            if (pro.equals(proveName)) {
//                                if (city.equals(cityName)) {
//                                    id = c.getId();
//
////                                    Bundle bundle=new Bundle();
////                                    bundle.putString("id",id);
////                                    fragment.setArguments(bundle);
////                                    ShowWeatherFragment.arId=id;
//                                    Log.i(RETROFIT_LOG,"id:"+id+"我们找到了！！！");
//                                    //
//                                    dbManager.insertIntoId(cityName,proveName,id);
//                                    if (tag==1)
//                                          query(id);
//                                    break;
//                                }
//                            }
//                        }
//
//                    }
//                });
//
//
//
//
//
//
//    }


    //return the weather info of the city
    //@param : the picked city name
    public void   query(String clickedCityId){
        Log.i(RETROFIT_LOG,clickedCityId+" id");
        //network
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
               // .client(whichClient(OkhttpCache.OKHTTP_TAG))
                .client(whichClient(OkhttpCache.OKHTTP_TAG))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        UrlQuery urlQuery=retrofit.create(UrlQuery.class);
        //指定 在主线程和io线程 两个同时进行 queryinfo 的值可能为0...怎么解决？？
        //subscribeOn:指定suubscribe()所发生的线程,即Observable.OnSubscribe被激活发生的线程，事件产生的线程
        //observeON()指定Subscriber所运行的线程，或者叫事件消费的线程  ，即onNext()......
        urlQuery.getKey(clickedCityId,"d2521b48a0774171a8b8defafac3c86e ")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<WeatherBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i(RETROFIT_LOG,"completed...weatherInfo");

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i(RETROFIT_LOG,"error......weatherInfo");
                    }


                    @Override
                    public void onNext(WeatherBean dataBean) {
                        Log.i(RETROFIT_LOG,"start...weatherInfo");
                        int i=0;
                        Log.i(RETROFIT_LOG,"okjhttp_log="+OkhttpCache.OKHTTP_TAG);
                        List<WeatherBean.HeWeatherDataServiceBean> lists=new ArrayList<WeatherBean.HeWeatherDataServiceBean>();
                        lists=dataBean.getHeWeatherDataService();

                        int t=(int) (new Random().nextInt(10));
                        int backId=R.drawable.sun1;
                        switch (t){
                            case 1:
                                backId=R.drawable.rain1;
                                break;
                            case 2:
                                backId=R.drawable.rain2;
                                break;
                            case 3:
                                backId=R.drawable.rain3;
                                break;
                            case 4:
                                backId=R.drawable.snow;
                                break;
                            case 5:
                                backId=R.drawable.uu;
                                break;
                            case 6:
                                backId=R.drawable.sun1;
                                break;
                            case 7:
                                backId= R.drawable.rain4;
                                break;
                            case 8:
                                backId=R.drawable.rain5;
                                break;
                            case 9:
                                backId=R.drawable.rain6;
                                break;
                            case 10:
                                backId= R.drawable.rain7;
                                break;
                        }
                        fragment.linearLayout.setBackgroundResource(backId);
                        fragment.linearLayout.getBackground().setAlpha(200);

                        for (WeatherBean.HeWeatherDataServiceBean list:lists) {
                            Log.i(RETROFIT_LOG,"size"+i++);
                           // fragment.city.setText("城市  ："+list.getBasic().getCity().toString());
                            Log.i(RETROFIT_LOG,"showweathe");
                            fragment.tmp.setText(list.getNow().getTmp().toString());
                           // fragment.fl.setText("体感温度："+list.getNow().getFl().toString());
                            fragment.txt.setText(list.getNow().getCond().getTxt());
                            //fragment.pcpn.setText(list.getNow().getPcpn());
                            //   cond.setText(list.getNow().getCond().toString());
                            List<WeatherBean.HeWeatherDataServiceBean.HourlyForecastBean> hourlyForecastBeen=list.getHourlyForecast();
                            ArrayList<Integer> tmp=new ArrayList<Integer>();
                            ArrayList<String>  dailyHour=new ArrayList<String>();
                            for (WeatherBean.HeWeatherDataServiceBean.HourlyForecastBean h:hourlyForecastBeen){
                                String mm=h.getDate().substring(10,h.getDate().length()-1);
                                dailyHour.add(mm);
                                int mp=Integer.parseInt(h.getTmp());
                                tmp.add(mp);

                            }
                            fragment.lineChartManager.setTmp(tmp);
                            fragment.lineChartManager.setDailyHour(dailyHour);
                            fragment.lineChartManager.initSingleLIneChart(fragment.lineChart1);
                            List<WeatherBean.HeWeatherDataServiceBean.DailyForecastBean> dailyForecastBeanList=list.getDailyForecast();
                            //获取所有的预报  用于折线图显示
                            ArrayList<Integer> max=new ArrayList<Integer>();
                            ArrayList<Integer> min=new ArrayList<Integer>();
                            ArrayList<String> date=new ArrayList<String>();
                            for (WeatherBean.HeWeatherDataServiceBean.DailyForecastBean d:dailyForecastBeanList){
                                date.add(d.getDate());
                                Log.i(RETROFIT_LOG,"s"+d.getDate());
                                int ma=Integer.parseInt(d.getTmp().getMax());
                                int mi=Integer.parseInt(d.getTmp().getMin());
                                Log.i(RETROFIT_LOG,ma+" s "+mi);
                                max.add(ma);
                                min.add(mi);

                            }
                            fragment.lineChartManager.setDate(date);
                            fragment.lineChartManager.setMax(max);
                            fragment.lineChartManager.setMin(min);
                            fragment.lineChartManager.initLineChart(fragment.lineChart);
                            //
                            String txD=dailyForecastBeanList.get(0).getCond().getTxtD();
                            fragment.oneState.setText(dailyForecastBeanList.get(0).getCond().getTxtD());
                            fragment.oneWeather.setText(dailyForecastBeanList.get(0).getTmp().getMax()+"/"+dailyForecastBeanList.get(0).getTmp().getMin());
                            fragment.twoWeather.setText(dailyForecastBeanList.get(1).getCond().getTxtD().toString());
                            fragment.twoState.setText(dailyForecastBeanList.get(1).getTmp().getMax()+"/"+dailyForecastBeanList.get(1).getTmp().getMin());
                            fragment.threeState.setText(dailyForecastBeanList.get(2).getCond().getTxtD());
                            fragment.threeWeather.setText(dailyForecastBeanList.get(2).getTmp().getMax()+"/"+dailyForecastBeanList.get(2).getTmp().getMin());
                            WeatherBean.HeWeatherDataServiceBean.SuggestionBean suggestionBean=list.getSuggestion();
                            fragment.suggest1.setText(suggestionBean.getDrsg().getTxt());
                            fragment.suggest3.setText(suggestionBean.getUv().getTxt());
                            fragment.suggest2.setText(suggestionBean.getTrav().getTxt());
                            fragment.driveSuggest.setText(suggestionBean.getCw().getTxt());
                            fragment.sportSuggest.setText(suggestionBean.getSport().getTxt());
                            fragment.flud.setText(suggestionBean.getFlu().getTxt());
                            //WeatherBean.HeWeatherDataServiceBean.DailyForecastBean dailyForecastBean=list.getDailyForecast();


                        }
                        //// TODO: 16-10-28   daily forcast.....
                        //WeatherBean.HeWeatherDataServiceBean.DailyForecastBean dailyForecastBean=
                        Log.i(RETROFIT_LOG,"success");
                        OkhttpCache.OKHTTP_TAG=1;
                    }
                });
     //   Log.i(RETROFIT_LOG,"size :"+queryInfo.size());

    }
    //chaxun  city id


}

