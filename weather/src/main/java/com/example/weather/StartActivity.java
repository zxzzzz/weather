package com.example.weather;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;

/**
 * Created by zx on 16-10-14.
 */
public class StartActivity extends FragmentActivity implements ShowWeatherFragment.AddCityListener{
    // 1.检测是否有默认的城市-返回列表id(ShoWeatherFragment)  -选择城市(ChooseCityFragment)
    //2.viewpager fragmentManager
    static String START_LOG = "start_log";
    public DbManager dbManager;
    public static ArrayList<ShowWeatherFragment> fragments = new ArrayList<ShowWeatherFragment>();
    CityIdDbHelper cityIdDbHelper;
    SQLiteDatabase sqLiteDatabase;
    //  ArrayList<CityInfo>  cityIds=new ArrayList<CityInfo>();//保存的城市信息
    ViewPager viewPager;

//    @Override
//    public void hourWeather() {
//        Intent intent=new Intent(StartActivity.this,LineChartManager.class);
//        startActivity(intent);
//    }

    FragmentManager fragmentManager;
    public static  MyPagerAdapter myPagerAdapter;
//    View view=getWindow().getDecorView();
    //重新加载页面

//    @Override
//    public void refresh(ShowWeatherFragment fragment) {
//        Log.i(START_LOG, "刷新天气");
//        dbManager.queryWeatherInfo(fragment);
//
//    }


    //开启 chooseCityFragment界面～
//如果是第一次添加  程序没有执行 viepager那一段的代码  如何执行一次？？
    //tag 0代表第一次添加
    @Override
    public void add(int tag) {
//        setContentView(R.layout.mm);
//        test2=(TextView)findViewById(R.id.test2);
//        ArrayList<Fragment> fragments= (ArrayList<Fragment>) fragmentManager.getFragments();
//        for (Fragment fragment:fragments)
//            fragmentManager.beginTransaction().remove(fragment).commit();
//        ChooseCityFragment c=new ChooseCityFragment();
//        FragmentTransaction transaction=fragmentManager.beginTransaction();
//        transaction.add(R.id.chooseCity,c).commit();
//        fragments=(ArrayList<Fragment>)fragmentManager.getFragments();
//        Log.i(START_LOG,"size:"+fragments.size());
//
//        Log.i(START_LOG,"add");

        Intent intent = new Intent(StartActivity.this, PrefActivity.class);
        intent.putExtra("which", "add");
        intent.putExtra("tag",tag);
        startActivity(intent);
    }


    @Override
    public void delete() {
        Log.i(START_LOG,"delete");
        Intent intent = new Intent(StartActivity.this, PrefActivity.class);
//        String transitionName = getString(R.string.transition_album_cover);
//        ActivityOptionsCompat options =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
//                        albumCoverImageView,   // The view which starts the transition
//                        transitionName    // The transitionName of the view we’re transitioning to
//                );
        intent.putExtra("which", "delete");
        startActivity(intent);

    }

    @Override
    public void share() {
        Intent intent = new Intent(StartActivity.this, PrefActivity.class);
        intent.putExtra("which", "share");
        startActivity(intent);

    }

    @Override
    public void setting() {
        Intent intent = new Intent(StartActivity.this, PrefActivity.class);
        intent.putExtra("which", "setting");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity_layout1);

        final View view=getWindow().getDecorView();
        final Handler handler=new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 200:
                        hideSystemUI(view);
                        break;
                }
            }
        };
         view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                Log.i(START_LOG,"viewChange");
                // Note that system bars will only be "visible" if none of the
                // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    // TODO: The system bars are visible. Make any desired
                    // adjustments to your UI, such as showing the action bar or
                    // other navigational controls.
                    Log.i(START_LOG,"showSystemui");
                    showSystemUI(view);
                    handler.sendEmptyMessageDelayed(200,2000);
                } else {
                    // TODO: The system bars are NOT visible. Make any desired
                    // adjustments to your UI, such as hiding the action bar or
                    // other navigational controls.
                    Log.i(START_LOG,"hide");
                    hideSystemUI(view);
                }
            }
        });
        cityIdDbHelper = new CityIdDbHelper(this, "city.db", null, 1);
        sqLiteDatabase = cityIdDbHelper.getWritableDatabase();
        dbManager = new DbManager(sqLiteDatabase);
        fragmentManager = getSupportFragmentManager();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myPagerAdapter = new MyPagerAdapter(fragmentManager, fragments);


        //接受哪个菜单的intent
//        Intent intent=getIntent();
//            String des = intent.getStringExtra("prefActivity");
//        if(des!=null){
//            ///////选项菜单选择完之后返回
//            switch (des) {
//                case "add":
//                    break;
//                case "delete":
//
//                    break;
//                case "share":
//                    break;
//                case "setting":
//                    break;
//            }
//        }

        //展示天气列表
        if (loadShowOrChoose()) {
            Log.i(START_LOG, "显示天气");
            //   setContentView(R.layout.start_activity_layout);
            //初始化viewPager
            //   viewPager=new ViewPager(this);

            //fragment列表
            // android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
            for (CityInfo cityId1 : SavedCity.cityInfos) {
                //根据cityid分别初始化 fragment
                Log.i(START_LOG,"开始"+cityId1.getId());
                ShowWeatherFragment fragment = ShowWeatherFragment.newInstance(cityId1.getId(),cityId1.getCity(), cityId1.getProv());
//                RetrofitManager retrofitManager=new RetrofitManager(fragment);
//                Log.i(START_LOG,"看看谁在前");
//                Log.i(START_LOG,"fragments");
//                retrofitManager.queryCityIds();
//                Bundle bundle=new Bundle();
//                //又被异步加载给坑了。。。。。。。。。。但是java又没有引用参数
//                bundle.putString("id",ShowWeatherFragment.arId);
//                fragment.setArguments(bundle);
                Log.i(START_LOG,"我增加了。。。");
                fragments.add(fragment);
            }
            //不能使用 android.app.fragmentManager 做参数吗？
//            viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
//                @Override
//                public Fragment getItem(int position) {
//                    Log.i(START_LOG, "第" + position + "ge");
//                    if (position==0){
//                        ShowWeatherFragment fragment3 = fragments.get(position);
//                        String id = fragment3.getArguments().getString("cityid");
//                        Log.i(START_LOG, position + " chengshi :" + id);
//                        dbManager.queryWeatherInfo(fragment3);
//
//                    }
//                    return fragments.get(position);
//                }

/*
    滑动过程中适配器默认会把前一个之前的item destroy掉，
    所以当滑动回来时就依然会重新加载。
    也就是还会执行一次onCreateView的方法。
    分析其原因就是适配器销毁了之前的item，自然解决办法就是不让他销毁。
 */
//                @Override
//                public void destroyItem(ViewGroup container, int position, Object object) {
//                    //super.destroyItem(container, position, object);
//                }
//
//                @Override
//                public int getCount() {
//                    Log.i(START_LOG, "size:" + SavedCity.cityInfos.size());
//                    return SavedCity.cityInfos.size();
//
//                }
//            });
            viewPager.setAdapter(myPagerAdapter);
            viewPager.setCurrentItem(0);
        //    viewPager.setOffscreenPageLimit(2);
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
//                    Log.i(START_LOG, "pageChange" + position);
//                    ShowWeatherFragment fragment3 = fragments.get(position);
//                    String id = fragment3.getArguments().getString("cityid");
//                    Log.i(START_LOG, position + " chengshi :" + id);
//                    dbManager.queryWeatherInfo(fragment3);
//                        String id=fragments.get(position).getArguments().getString("id");
//                    //给fragment添加bundle --id
//                        if (id==null){
//                            RetrofitManager r=new RetrofitManager(fragments.get(position));
//                            r.queryCityIds(2);
//                            String c=fragments.get(position).getArguments().getString("city");
//                            String p=fragments.get(position).getArguments().getString("prove");
//                            id=dbManager.queryCityId(p,c);
////                            Bundle bundle=new Bundle();
////                            bundle.putString("id",id);
//                        //    fragments.get(position).setArguments(bundle);
                    Log.i(START_LOG,"有了！！");
                StartActivity.this.getActionBar().setTitle(fragments.get(position).getArguments().getString("city"));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


        }
        //如果没有保存的城市，则跳转到 添加城市列表
        else {
//            setContentView(R.layout.mm);
//            //id 为fragmentManagershibiefragment的唯一标识符，也决定了fragment的视图应该出现在activity的什么地方
//            //FragmentLayout时fragment的容器视图，而fragment是将fragment添加到le布局中
//            //在代码中添加更加灵活.
//            //FragmentManager fragmentManager=getSupportFragmentManager();
//            Fragment fragment = fragmentManager.findFragmentById(R.id.chooseCity);
//            if (fragment == null) {
//                fragment = new ChooseCityFragment();
//                fragmentManager.beginTransaction().add(R.id.chooseCity, fragment).commit();
//            }
            add(0);

        }


    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus){
//            if (hasFocus) {
//                vi.setSystemUiVisibility(
//                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }

    private void hideSystemUI(View mDecorView) {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    // This snippet shows the system bars. It does this by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI(View mDecorView) {
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


    }

    //该加载显示天气的页面 或则选择城市的页面  前者为true
    public boolean loadShowOrChoose() {
        Log.i(START_LOG, "loadShowOrChoose");

        //查询 所有保存的city
        new SavedCity().getCityInfo();


        //得到从 StartActivity的返回值


        //如果有返回数据，则 iscity为true
        if (SavedCity.cityInfos.size() != 0) {
            return true;
        }
        return false;

    }



    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<ShowWeatherFragment> swf;

        public MyPagerAdapter(FragmentManager fm, ArrayList<ShowWeatherFragment> swf) {
            super(fm);
            this.swf = swf;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i(START_LOG,"destroyItem");
          //   super.destroyItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            //ShowWeatherFragment fragment3 = swf.get(position);
            Log.i(START_LOG,"getItem:"+position);
            return swf.get(position);
        }

        @Override
        public int getCount() {
            return swf.size();
        }

//        @Override
//        public int getItemPosition(Object object) {
//            Log.i(START_LOG,"getItemPosition");
//            return PagerAdapter.POSITION_NONE;
//        }
    }

}

//

