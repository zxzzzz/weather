package com.example.weather;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zx on 16-10-17.
 */
public class DeleteAdapter extends BaseAdapter {
    static String DELETEADAPTE="adapter";
    ArrayList<CityInfo> deleteItems = new ArrayList<CityInfo>();
    Context context;
    LayoutInflater inflater;
    ArrayList<Integer> state;//状态为0 ，image 不可见 ，状态为1 ，image可见

    public DeleteAdapter(ArrayList<CityInfo> deleteItems, Context context,ArrayList<Integer>  state) {
        this.deleteItems = deleteItems;
        this.context = context;
        this.state=state;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            viewHolder=new ViewHolder();
            inflater= LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.item_delete, parent,false);
            viewHolder.city=(TextView)convertView.findViewById(R.id.city_name);
            viewHolder.imageView=(ImageView)convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        CityInfo d=deleteItems.get(position);
        Log.i(DELETEADAPTE,"d:"+d.getCity());
        viewHolder.city.setText(d.toString());
        Log.i(DELETEADAPTE,"state="+state);
        if (state.get(state.size()-1)==0){
            viewHolder.imageView.setVisibility(View.INVISIBLE);
        }
        else if (state.get(state.size()-1)==1){
            viewHolder.imageView.setVisibility(View.VISIBLE);
        }
        return convertView;

    }

    @Override
    public int getCount() {
        return deleteItems.size();
    }

    @Override
    public Object getItem(int position) {

        return deleteItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView city;//要显示的城市名称
        ImageView imageView;
    }
}
