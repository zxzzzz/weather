package com.example.weather;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by zx on 16-10-13.
 */
public class DbManager {
    SQLiteDatabase sqLiteDatabase;
    static final String DBMANAGER="dbmanager_log";
    public DbManager(SQLiteDatabase sqLiteDatabase) {

        this.sqLiteDatabase = sqLiteDatabase;
    }

    //根据输入的城市名称查询 城市名称正则匹配
    //// TODO: 16-10-23
    public CityInfo queryCityName(String inputName){
        String c=null;
        String p=null;
        String id=null;
        Cursor cursor=sqLiteDatabase.rawQuery("select * from city_info where city=?",new String [] {inputName});
        if (cursor.moveToFirst()){
            int pos0=cursor.getColumnIndex("cityid");
            int pos1=cursor.getColumnIndex("city");
            int pos2=cursor.getColumnIndex("prove");
            id=cursor.getString(pos0);
            Log.i(DBMANAGER,"query id:"+id);
             c=cursor.getString(pos1);
             p=cursor.getString(pos2);

        }
        return new CityInfo(id,c,p);


    }

    //根据查询到的城市名称查询城市 id
//    //// TODO: 16-10-23
//    public String queryCityId(String prove,String city){
//        String cityId=null;
//        Cursor cursor=sqLiteDatabase.rawQuery("select cityid from save_city where prove=? and city=?",new String[]{prove,city});
//        if (cursor.moveToFirst()) {
//            int position = cursor.getColumnIndex("id");
//            cityId = cursor.getString(position);
//        }
//        else {
//            Log.i("no hava id", "no id");
//            return null;
//        }
//        return cityId;
//    }

    //根据cityid查询天气信息
    //不知道怎么解决RXjava的线程问题 ，先返回null
//    public void queryWeatherInfo(ShowWeatherFragment fragment){
//        RetrofitManager manager=new RetrofitManager(fragment);
//        manager.queryCityIds();
//        //manager.query(id);
//
//    }

    //删除选中的城市   根据id 在  svae_city.table

     public void deleteCityInfo(String cityid){
         sqLiteDatabase.execSQL("delete from save_city where cityid =?",new String[]{cityid});
     }

    //插入选中的城市 cityName,proveName 在 save_city.table

    public    void insertCityInfo(String id,String cityName,String proveName){
        //查询要存储的城市的代号  在cityid表中
     //   Cursor cursor=sqLiteDatabase.rawQuery("select city,prove from city_info where city=?",new String[]{cityName});
       // String city=null;
        //String prove=null;
        //if (cursor.moveToFirst()) {
          //  int pos = cursor.getColumnIndex("city");
            //int pos2=cursor.getColumnIndex("prove");
            //cityInfos.add(new CityInfo(cursor.getString(pos),cursor.getString(pos2)));
           // city=cursor.getString(pos);
            //prove=cursor.getString(pos2);
        //}
     //   sqLiteDatabase.execSQL("insert into save_city values(?,?)",new String[]{"CN101020100","上海"});

//        Cursor cursor=sqLiteDatabase.rawQuery("select * from city where cityid=?",new String[]{id});
//        if (cursor.moveToFirst()==false) {
            Log.i(DBMANAGER,"cityid:"+id);
            sqLiteDatabase.execSQL("insert into save_city values(?,?,?)", new String[]{id,cityName, proveName});
            // sqLiteDatabase.execSQL("insert into save_city values(?,?)",new String[]{"CN101020300","宝山"});

    }
//将id插入
    //if exists(select logout from card where books='Y') insert into books values('1')
//    public void insertIntoId(String id,String cityName,String proveName){
//        Cursor cursor=sqLiteDatabase.rawQuery("select * from save_city where cityid=?",new String []{id});
//        if (cursor.moveToFirst()==false) {
////            ContentValues contentValues = new ContentValues();
////            contentValues.put("cityid",id);
//            //sqLiteDatabase.execSQL("update save_city set cityid= ? where prove=? and where city=?", new String[]{"3e", "rr", "ssss"});
//            sqLiteDatabase.execSQL("insert save_city(cityid) values(?,?,?) where cityid=?",new String[]{id,id,cityName,proveName});
//        }
//    }

    //查询是否有已经保存的city 并返回信息
    public ArrayList<CityInfo> hasCitises(){
        ArrayList<CityInfo> cityInfos=new ArrayList<CityInfo>();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from save_city ",null);
        //如果查询到数据

        if (cursor.moveToFirst())
        {
            do{

                int pos0=cursor.getColumnIndex("cityid");
                int pos=cursor.getColumnIndex("city");
                int pos2=cursor.getColumnIndex("prove");
                String id=cursor.getString(pos0);
                String city=cursor.getString(pos);
                String prove=cursor.getString(pos2);
             //   int pos3=cursor.getColumnIndex("cityid");
                cityInfos.add(new CityInfo(id,city,prove));

            }while (cursor.moveToNext());
        }
        return cityInfos;


    }

    //将所有的城市信息插入到 city.info表中
    public void insertAllCityInfo(String cityid,String cityName,String proveName){
        sqLiteDatabase.execSQL("insert into city_info values(?,?,?)",new String[]{cityid,cityName,proveName});

    }

}
