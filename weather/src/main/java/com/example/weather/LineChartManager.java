package com.example.weather;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.UnicodeSet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zx on 16-10-28.
 */
//折线图样式类
public class LineChartManager  {
    private  ArrayList<String> date;//日期
    private ArrayList<Integer> max;//最高气温
    private ArrayList<Integer> tmp;//每小时温度

    public void setDailyHour(ArrayList<String> dailyHour) {
        this.dailyHour = dailyHour;
    }

    public void setTmp(ArrayList<Integer> tmp) {
        this.tmp = tmp;
    }

    private ArrayList<String>  dailyHour;//小时

    public ArrayList<String> getDailyHour() {
        return dailyHour;
    }

    public ArrayList<Integer> getTmp() {
        return tmp;
    }

    public LineChartManager(Context context) {
        this.context = context;
    }

    private ArrayList<Integer> min;//最低气温
    private Context context;



    public ArrayList<Integer> getMax() {
        return max;
    }

    public ArrayList<Integer> getMin() {
        return min;
    }

    public ArrayList<String> getDate() {
        return date;
    }

    public void setDate(ArrayList<String> date) {
        this.date = date;
    }

    public void setMax(ArrayList<Integer> max) {
        this.max = max;
    }

    public void setMin(ArrayList<Integer> min) {
        this.min = min;
    }

    public void initDataStyle(LineChart lineChart){
        Description description=new Description();
        description.setText("未来一周天气预报");
        lineChart.setDescription(description);
        //设置x轴样式
       IAxisValueFormatter iAxisValueFormatter=new IAxisValueFormatter() {
           @Override
           public String getFormattedValue(float value, AxisBase axis) {
               return date.get((int)value);
           }

           @Override
           public int getDecimalDigits() {
               return 0;
           }

       };
        XAxis xAxis=lineChart.getXAxis();
        xAxis.setTextSize(14.0f);
        xAxis.setTextColor(Color.BLACK);
     //   xAxis.setDrawAxisLine(true);

        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setValueFormatter(iAxisValueFormatter);
        xAxis.setEnabled(true);
        //设置y轴样式
        YAxis l=lineChart.getAxisLeft();
        l.setEnabled(false);
        YAxis r=lineChart.getAxisRight();
        r.setEnabled(false);



    }
    public void initSingleLIneChart(LineChart lineChart){
        initDailyStyle(lineChart);
        ArrayList<Entry> entry=new ArrayList<>();
        for (int i=0;i<tmp.size();i++){
            entry.add(new Entry(i,tmp.get(i)));
        }
        LineDataSet lineDataSet=new LineDataSet(entry,"每小时预报");
        List<ILineDataSet> lineDataSets=new ArrayList<>();
        lineDataSets.add(lineDataSet);
        LineData lineData=new LineData(lineDataSets);
        lineChart.setData(lineData);
    }

    public void initDailyStyle(LineChart lineChart){
        //
        Description description=new Description();
        description.setText("24小时预报");
        lineChart.setDescription(description);
        //设置x轴样式
        IAxisValueFormatter iAxisValueFormatter=new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dailyHour.get((int)value);
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }

        };
        XAxis xAxis=lineChart.getXAxis();
        xAxis.setTextSize(14.0f);
        xAxis.setTextColor(Color.BLACK);
        //   xAxis.setDrawAxisLine(true);

        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setValueFormatter(iAxisValueFormatter);
        xAxis.setEnabled(true);
        //设置y轴样式
        YAxis l=lineChart.getAxisLeft();
        l.setEnabled(false);
        YAxis r=lineChart.getAxisRight();
        r.setEnabled(false);

    }
    public void initLineChart(LineChart lineChart){
        initDataStyle(lineChart);
        ArrayList<Entry> entryMax=new ArrayList<Entry>();
        ArrayList<Entry>  entryMin=new ArrayList<Entry>();
        for (int i=0;i<max.size();i++){
            entryMax.add(new Entry(i,max.get(i)));
            entryMin.add(new Entry(i,min.get(i)));
        }
        LineDataSet dataSet1=new LineDataSet(entryMax,"最高气温");
        //// TODO: 16-10-28 设置 折线图样式
        dataSet1.setColor(Color.RED);
        LineDataSet dataSet2=new LineDataSet(entryMin,"最低气温");
        //// TODO: 16-10-28
        List<ILineDataSet> lineDataSets=new ArrayList<>();
        lineDataSets.add(dataSet1);
        lineDataSets.add(dataSet2);
        LineData lineData=new LineData(lineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();

    }
}
