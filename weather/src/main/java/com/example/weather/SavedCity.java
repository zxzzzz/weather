package com.example.weather;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by zx on 16-10-17.
 */
//所有已保存的城市
public class SavedCity {
    String path="/data/data/com.example.weather/databases/city.db";
    SQLiteDatabase sqlietDatabase=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);

    DbManager dbManager=new DbManager(sqlietDatabase);

    public static ArrayList<CityInfo>  cityInfos=new ArrayList<CityInfo>();
    public ArrayList<CityInfo> getCityInfo(){

        cityInfos=dbManager.hasCitises();
        return cityInfos;
    }
    //// TODO: 16-10-17
//    public static void add(CityInfo c){
//        cityInfos.add(c);
//
//    }
//    public static void delete(CityInfo c)
//    {
//        cityInfos.remove(c);
//    }
}
