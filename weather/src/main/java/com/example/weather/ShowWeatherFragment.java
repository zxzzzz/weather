package com.example.weather;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.logging.LogRecord;

/**
 * Created by zx on 16-10-14.
 */
//展示天气情况 viewpager --可滑动翻页
public class ShowWeatherFragment extends android.support.v4.app.Fragment {

    //参数只有id就不用刷新页面时多一次网络请求了
    //okhttp加载网络是异步加载
    static String SHOWWEATHERFRAGMENT="show_weather";
    LineChartManager lineChartManager;
    //之前把 testView设成static了，全局共享....每个showweatherFragment共用一个 testView
    TextView city;
    TextView tmp;
    TextView fl;
    TextView txt;
    TextView pcpn;
    ImageView oneImage;
    TextView oneState;
    TextView oneWeather;
    ImageView twoImage;
    TextView twoState;
    TextView twoWeather;
    ImageView threeImage;
    TextView threeState;
    TextView threeWeather;
    TextView suggest1;
    TextView suggest2;
    TextView suggest3;
    TextView sportSuggest;
    TextView flud;
    TextView driveSuggest;
    TextView hour24;
    LineChart lineChart;
    //背景图片
    ImageView imageView;
    LineChart lineChart1;
    RelativeLayout linearLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    RetrofitManager retrofitManager;
    AddCityListener addCityListener;
    String id;//24小时天气预报则线图

    public static String arId;

    //添加城市时 调用  chooseCityFragment
    public interface AddCityListener {
        public void add(int tag);

        public void delete();

        public void share();

        public void setting();
        //    public void refresh(ShowWeatherFragment fragment);
        //public void hourWeather();
    }

    //单例模式
    public static ShowWeatherFragment newInstance(String cityId, String cityName, String proveName) {
        ShowWeatherFragment showWeatherFragment = new ShowWeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cityid", cityId);
        bundle.putString("city", cityName);
        bundle.putString("prove", proveName);
        showWeatherFragment.setArguments(bundle);
        return showWeatherFragment;

    }

    //选项菜单
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.show_weather_menu, menu);
    }

    //点击选项菜单事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(getActivity(), "选择", Toast.LENGTH_SHORT).show();
                addCityListener.add(1);
                return true;
            case R.id.delete:
                addCityListener.delete();
                return true;
            case R.id.setting:
                addCityListener.setting();
                return true;
            case R.id.share:
                addCityListener.share();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //目前只展示 城市，气温，降雨量，天气状况
        Log.i(StartActivity.START_LOG, "oncreateView");
        View view = inflater.inflate(R.layout.show_weather_fragment, container, false);
        linearLayout = (RelativeLayout) view.findViewById(R.id.backLayout);
        //imageView=(ImageView)view.findViewById(R.id.backImage);
        // city=(TextView)view.findViewById(R.id.city);
        lineChartManager=new LineChartManager(getContext());
        lineChart1=(LineChart)view.findViewById(R.id.line2);
        tmp = (TextView) view.findViewById(R.id.tmp);
        //fl=(TextView)view.findViewById(R.id.fl);
        hour24=(TextView)view.findViewById(R.id.hour24);
        txt = (TextView) view.findViewById(R.id.txt);
        // pcpn=(TextView)view.findViewById(R.id.pcpn);
        flud = (TextView) view.findViewById(R.id.flud);
        oneImage = (ImageView) view.findViewById(R.id.todayImage);
        oneState = (TextView) view.findViewById(R.id.oneState);
        oneWeather = (TextView) view.findViewById(R.id.oneWeather);
        //  twoImage=(ImageView)view.findViewById(R.id.tomorryImage);
        twoState = (TextView) view.findViewById(R.id.twoWeather);
        twoWeather = (TextView) view.findViewById(R.id.twoState);
        //   threeImage=(ImageView)view.findViewById(R.id.);
        threeState = (TextView) view.findViewById(R.id.threeState);
        threeWeather = (TextView) view.findViewById(R.id.threeWeather);
        suggest1 = (TextView) view.findViewById(R.id.suggest1);
        suggest2 = (TextView) view.findViewById(R.id.suggest2);
        suggest3 = (TextView) view.findViewById(R.id.suggest3);
        sportSuggest = (TextView) view.findViewById(R.id.sportSuggest);
        driveSuggest = (TextView) view.findViewById(R.id.driveSuggest);
        retrofitManager = new RetrofitManager(this);
        id = this.getArguments().getString("cityid");
        lineChart=(LineChart)view.findViewById(R.id.line1);
        retrofitManager.query(id);
       // lineChartManager.initLineChart(lineChart);
//        hour24.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addCityListener.hourWeather();
//            }
//        });

//        final Handler handler=new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                //super.handleMessage(msg);
//                switch (msg.what){
//                    case 200:
//                        Toast.makeText(getActivity(),"刷新结束",Toast.LENGTH_SHORT).show();
//                        swipeRefreshLayout.setRefreshing(false);
//                        break;
//                }
//
//            }
//        };
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_light), getResources().getColor(android.R.color.holo_green_light), getResources().getColor(android.R.color.holo_red_light));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "正在刷新", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(true);
                Log.i(SHOWWEATHERFRAGMENT, "下拉刷新");
                OkhttpCache.OKHTTP_TAG = 2;
                Log.i(SHOWWEATHERFRAGMENT, "okhttp_tag=" + OkhttpCache.OKHTTP_TAG);
                //          addCityListener.refresh(ShowWeatherFragment.this);
                String city = ShowWeatherFragment.this.getArguments().getString("cityid");
                retrofitManager.query(city);
                Log.i(SHOWWEATHERFRAGMENT, "刷新结束");
                // handler.sendEmptyMessageDelayed(200,2000);
                OkhttpCache.OKHTTP_TAG=1;
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    //


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通知fragmentManager 调用选项菜单
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            addCityListener = (AddCityListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();

        }
    }

    public boolean equals(ShowWeatherFragment showWeatherFragment2) {
        if (showWeatherFragment2.getArguments().getString("cityid").equals(ShowWeatherFragment.this.getArguments().getString("cityid")))
            return true;
        return false;
    }



}












