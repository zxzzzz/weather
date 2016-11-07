package com.example.weather;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by zx on 16-10-17.
 */
//删除城市界面
public class DeleteFragment extends Fragment{
    static String DELETE_LOG="delete_log";
    ListView listView;
   // CityInfo delete;
    DeleteAdapter deleteAdapter;
    static  ArrayList<Integer>  state=new ArrayList<Integer>();
    ArrayList<CityInfo> deleteItems=new ArrayList<CityInfo>();
    static  CityInfo dt;
    DeleteCity delete;
   // ChooseCityFragment.SendFragment sendFragment;//发送给startActivity需要删除的 fragment
    public interface DeleteCity{
        public void delete(CityInfo cityInfo);
    }
    
    //向startActivity发送需要删除的fragment 

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//当点击 编辑 按钮时，改变状态，显示 imageView
        switch (item.getItemId()){
            case R.id.delete_m:
                state.add(1);
                Log.i(DELETE_LOG,"state:"+state);
                deleteAdapter.notifyDataSetChanged();
                break;
            case android.R.id.home:
                //// TODO: 16-10-19
                if (dt!=null) {
                    ShowWeatherFragment swf=ShowWeatherFragment.newInstance(dt.getId(),dt.getCity(),dt.getProv());
                  //  sendFragment.send(swf, 0);

                for (int i = 0; i < StartActivity.fragments.size(); i++) {
                    if (StartActivity.fragments.get(i).equals(swf)) {
                        Log.i(DELETE_LOG,"比较的："+StartActivity.fragments.get(i).getArguments().getString("city"));
                        StartActivity.fragments.remove(i);
                        Log.i(DELETE_LOG,"R.id.home");
                        Log.i(DELETE_LOG,"swf:"+swf.getArguments().getString("city"));
                        }
                }
                }

                getActivity().finish();
                StartActivity.myPagerAdapter.notifyDataSetChanged();

                return  true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_menu,menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.delete_fragment,container,false);
        Log.i(DELETE_LOG,"oncreate view");
        listView=(ListView)view.findViewById(R.id.listView);

        for (CityInfo c:SavedCity.cityInfos)
        {
            deleteItems.add(c);
        }
        Log.i(DELETE_LOG,"deleteItems size"+deleteItems.size());
        for (CityInfo d:deleteItems){
            Log.i(DELETE_LOG,"city:"+d.toString());
        }
        state.add(0);
        deleteAdapter=new DeleteAdapter(deleteItems,getActivity(),state);
     //   ArrayAdapter<String>  arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,deleteCity);
        listView.setAdapter(deleteAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //向activity传递需要删除的城市名称
                if (state.get(state.size()-1)==1) {
                    Log.i(DELETE_LOG, "city delete:" + deleteItems.get(position).getCity());
                    Log.i(DELETE_LOG, "click");
                    dt=deleteItems.get(position);
                    Log.i(DELETE_LOG, "删除了："+dt.toString());
                    //在数据库中删除
                    delete.delete(dt);
                    //保存的静态 列表中删除
                    SavedCity.cityInfos.remove(dt);
                    //通知adapter
                    deleteItems.remove(position);
                    deleteAdapter.notifyDataSetChanged();
                    //在数据库中删除


                }
//                Intent  intent=new Intent(getActivity(),StartActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("city",city.get(position).getCity());
//                bundle.putString("prove",city.get(position).getProv());
//                intent.putExtras(bundle);
//                startActivityForResult(intent,DELETE_REQUESTCODE);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            delete=(DeleteCity)context;
        }catch (ClassCastException c){
            c.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar=getActivity().getActionBar();
        //实现向上导航
        actionBar.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
    }

}
