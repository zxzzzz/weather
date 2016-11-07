package com.example.weather;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.health.ServiceHealthStats;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by zx on 16-10-14.
 */
//如果数据库里没有设置好的城市 ，就显示此fragemnt供用户选择并输入
public class ChooseCityFragment extends android.support.v4.app.Fragment {
    static String CHOOSE_TAG="choose_fragment";
    EditText input;
    TextView showCity;//点击了之后返回显示界面
//    Button beijing;
//    Button shanghai;
//    Button hangzhou;
//    Button nanjing;
    String chooseCity;//被选择的城市
    CityIdDbHelper cityDbHelper;
    SQLiteDatabase sqliteDatebase;
    DbManager dbManager;
  //  ConvertCity callFragment;
    CityInfo cityInfo;
    ChooseListener chooseListener;
    public interface  ChooseListener{
        public void choose();
    }

    //interface如何初始化？？？

    //c创建接口 ConvertCity,创建新的cityInfo ,并加入到已保存的集合 CityInfos中
//    public interface ConvertCity{
//        public void addCity(String cityName,String prove);
//    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                onBackPrs
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_fragment_layout, container,false);
        input = (EditText) view.findViewById(R.id.searchCity);
        showCity = (TextView) view.findViewById(R.id.findCity);
//        beijing = (Button) view.findViewById(R.id.beijing);
//        nanjing = (Button) view.findViewById(R.id.nanjing);
//        hangzhou = (Button) view.findViewById(R.id.hangzhou);
//        shanghai = (Button) view.findViewById(R.id.shanghai);
        cityDbHelper=new CityIdDbHelper(getActivity(),"city.db",null,1);
        sqliteDatebase=cityDbHelper.getWritableDatabase();
        dbManager=new DbManager(sqliteDatebase);
  //      ActionBar actionBar=getActivity().getActionBar();
        //    actionBar.setDisplayHomeAsUpEnabled(true);


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
//                    case R.id.beijing:
//                        chooseCity = beijing.getText().toString();
//                        input.setText(chooseCity);
//                     //   showInEdit();
//                        //根据选中的城市名称查询城市代号 ，并将其保存到save_city.table中
//                       //nextStep(chooseCity);
//                        break;
//                    case R.id.nanjing:
//                        chooseCity = nanjing.getText().toString();
//                        input.setText(chooseCity);
//                   //     showInEdit();
//                        //nextStep(chooseCity);
//                        break;
//                    case R.id.hangzhou:
//                        chooseCity = hangzhou.getText().toString();
//                        input.setText(chooseCity);
//                 //       showInEdit();
//                        //nextStep(chooseCity);
//                        break;
//                    case R.id.shanghai:
//                        chooseCity = shanghai.getText().toString();
//                        input.setText(chooseCity);
               //         showInEdit();
                       // nextStep(chooseCity);
//                        break;
                    case  R.id.findCity:
                        if (showCity.getText().toString()!=null){
                            Log.i(CHOOSE_TAG,"cityinfo"+cityInfo.getId());
                            nextStep(cityInfo.getId(),cityInfo.getCity(),cityInfo.getProv());
                        }
                        break;
                }
            }
        };
//        beijing.setOnClickListener(clickListener);
//        shanghai.setOnClickListener(clickListener);
//        hangzhou.setOnClickListener(clickListener);
//        nanjing.setOnClickListener(clickListener);
        showCity.setOnClickListener(clickListener);
        input.addTextChangedListener(textWatcher);
        return view;

    }

    //监听 EditText内容变化
    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            chooseCity=input.getText().toString();
            showInEdit();

        }
    };
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            callFragment=(ConvertCity)context;
//        }catch (ClassCastException c){
//            c.printStackTrace();
//        }
//    }
    public void nextStep(String cityid,String cityname,String proveName){

        dbManager.insertCityInfo(cityid,cityname,proveName);
       // callFragment.addCity(cityname,proveName);
        CityInfo c=new CityInfo(cityid,cityname,proveName);
        SavedCity.cityInfos.add(c);
       // dbManager.insertCityInfo(cityname,proveName);
       // RetrofitManager r=new RetrofitManager(newS);
     //   r.queryCityIds(2);
//        String cii=newS.getArguments().getString("city");
//        String p=newS.getArguments().getString("prove");
//        String id=newS.getArguments().getString("cityid");
         //所有的view都要重绘？？？？？
        int tag=this.getArguments().getInt("tag");
        if (tag==1) {
            ShowWeatherFragment newS=ShowWeatherFragment.newInstance(cityid,cityname,proveName);
            StartActivity.fragments.add(newS);
            getActivity().finish();
            StartActivity.myPagerAdapter.notifyDataSetChanged();
        }
        //如果tag==0,则销毁前一个活动并重建
        else if (tag==0){
            chooseListener.choose();
        }

    };
    //显示在editText上
    public void  showInEdit(){
       // input.setText(chooseCity);
        //查找匹配的城市名字，降省分显示在TextView上
        cityInfo=dbManager.queryCityName(chooseCity);
        showCity.setText(cityInfo.toString());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            chooseListener=(ChooseListener)context;
        }catch (ClassCastException c){
            c.printStackTrace();
        }
    }
}


