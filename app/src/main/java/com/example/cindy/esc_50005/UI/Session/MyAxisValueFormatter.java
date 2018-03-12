package com.example.cindy.esc_50005.UI.Session;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by 1002215 on 8/3/18.
 */

public class MyAxisValueFormatter implements IAxisValueFormatter {

    private DecimalFormat mFormat;

    public MyAxisValueFormatter(){
        mFormat = new DecimalFormat("######.0");

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) + "$";
    }
}
