package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/**
 * Created by zx on 16-10-17.
 */
public class PrefActivity extends FragmentActivity implements DeleteFragment.DeleteCity,ChooseCityFragment.ChooseListener{
    FragmentManager fragmentManager;
    CityIdDbHelper cityIdDbHelper;
    SQLiteDatabase sqLiteDatabase;
    DbManager dbManager;
    static String PREFACTIVITY="prefeActivity";
    @Override
    public void delete(CityInfo cityInfo) {
        dbManager.deleteCityInfo(cityInfo.getId());
    }

//    @Override
//    public void addCity(String cityName, String prove) {
//            CityInfo c=new CityInfo(cityName,prove);
//            SavedCity.cityInfos.add(c);
//            dbManager.insertCityInfo(cityName,prove);
//            Intent i=new Intent(PrefActivity.this,StartActivity.class);
//            i.putExtra("prefActivity","add");
//            startActivity(i);
//
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref_activity);
        cityIdDbHelper=new CityIdDbHelper(this,"city.db",null,1);
        sqLiteDatabase=cityIdDbHelper.getWritableDatabase();
        dbManager=new DbManager(sqLiteDatabase);
        fragmentManager=getSupportFragmentManager();
        //获得上一个Activity 的额外信息
        String whichFragment=getIntent().getStringExtra("which");
        switch (whichFragment){
            case "add":
                int tag=getIntent().getIntExtra("tag",1);
                Fragment chooseCityFragment=fragmentManager.findFragmentByTag("add");
                if (chooseCityFragment==null) {
                    chooseCityFragment = new ChooseCityFragment();
                    Bundle bundle=new Bundle();
                    bundle.putInt("tag",tag);
                    chooseCityFragment.setArguments(bundle);
                }
              //  fragmentManager.beginTransaction().add(R.id.fragment,chooseCityFragment,"add").commit();
//                fragmentManager.beginTransaction().addToBackStack("add");
                jumpToFragmet(chooseCityFragment);
                break;
            case "delete":
                Fragment deleteFragment=fragmentManager.findFragmentByTag("delete");
                if (deleteFragment==null)
                    deleteFragment=new DeleteFragment();
          //      fragmentManager.beginTransaction().add(R.id.fragment,deleteFragment,"delete");
            //    Log.i(PREFACTIVITY,"delete end");
                jumpToFragmet(deleteFragment);
                break;
            case "share":
                //// TODO: 16-10-17
                break;
            case "setting":
                Fragment settingFragment=fragmentManager.findFragmentByTag("setting");
                if (settingFragment==null)
                    settingFragment=new SettingFragment();
                fragmentManager.beginTransaction().add(R.id.fragment,settingFragment).commit();
                break;

        }

    }
    public void jumpToFragmet(Fragment fragment){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void choose() {
        Intent intent=new Intent(PrefActivity.this,StartActivity.class);
        startActivity(intent);
    }
}
