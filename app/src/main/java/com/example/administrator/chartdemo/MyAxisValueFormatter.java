package com.example.administrator.chartdemo;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/5/27.
 */

public class MyAxisValueFormatter implements IAxisValueFormatter
{

	private DecimalFormat mFormat;

	public MyAxisValueFormatter() {
//		mFormat = new DecimalFormat("###,###,###,##0.0");
		mFormat = new DecimalFormat("###,###,###,##0");
	}

	@Override
	public String getFormattedValue(float value, AxisBase axis) {
//		return mFormat.format(value) + " $";
		return mFormat.format(value);
	}
}
