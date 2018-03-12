package com.example.cindy.esc_50005.UI.Session;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cindy.esc_50005.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by 1002215 on 20/2/18.
 */

public class ActivitiesFragment extends android.support.v4.app.Fragment {

    private BarChart mChart;



    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_progress_main, container, false);
        mChart = (BarChart) view.findViewById(R.id.chart1);
        mChart.getDescription().setEnabled(false);
        mChart.setMaxVisibleValueCount(40);


        final String[] ds = new String[5];
        ds[0]="Quiz 1";
        ds[1]="Quiz 2";
        ds[2]="Quiz 3";
        ds[3]="Quiz 4";
        ds[4]="Quiz 5";


        XAxis xval = mChart.getXAxis();
        xval.setDrawLabels(true);
        xval.setValueFormatter(new MyAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.i("zain", "value " + value);
                return ds[Math.round(value)];
            }

//            @Override
//            public int getDecimalDigits() {
//                return 0;
//            }
        });

        setData(5);
        mChart.setFitBars(true);
        return view;
    }

    private void setData(int count){
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

        for(int i=0; i<count; i++){
            float value = (float) (Math.random()*100);
            yVals.add(new BarEntry(i,value));
        }

        BarDataSet set = new BarDataSet(yVals, "(Quizzes)");
        set.setDrawIcons(false);
        set.setColors(ColorTemplate.LIBERTY_COLORS);
        //Show value on top of each bar
        set.setDrawValues(true);
        set.setValueTextSize(15);

        BarData data = new BarData(set);
        data.setValueFormatter(new MyValueFormatter());
        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(500);
    }


}
