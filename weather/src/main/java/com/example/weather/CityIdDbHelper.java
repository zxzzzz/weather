package com.example.weather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

/**
 * Created by zx on 16-10-11.
 */
//创建存储cityid的db
public class CityIdDbHelper extends SQLiteOpenHelper {
    //城市代号 城市名称（中英） 所属地区 所属省份,id
  //  static List<CityInfo> cityInfoList=new ArrayList<CityInfo> ();
    //创建 cityid表 存储所有的城市名称代号省份等信息
    final String CREATE_TABLE="create table city_info(cityid varchar(255) primary key,city varchar(255),"+"prove varchar(255))";
    //创建 save_city.table 存储用户选中的城市名称与id
    String CREATE_CITYINFO_TABLE="create table save_city(cityid varchar(255) primary key,city varchar(255) , prove varchar(255))";

    public CityIdDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    //数据库首次构建时调用该函数
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_CITYINFO_TABLE);
        //初次创建时将城市信息写入
        RetrofitManager.insertCityInfoToTable();
//        String cityAll;
//        String proveAll;
        //store();
    //    cityInfoList=new AllCityInfo().getCityInfoArrayList();
//        for (CityInfo cityInfo:cityInfoList){
//            cityAll=cityInfo.getCity();
//            proveAll=cityInfo.getProv();
//            //向db中插入城市数据信息
//            db.execSQL("insert into city_info values(?,?)",new String[]{cityAll,proveAll});
//        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //存所有cityid信息



}
