package com.example.administrator.chartdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/27.
 */

public class BarChartManager {
	static Typeface mTfLight;

public static void setBarChart(BarChart mLineChart, Context mContext){

		mTfLight = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Light.ttf");
		mLineChart.setDrawBarShadow(false);//是否显示背景阴影
		mLineChart.setDrawValueAboveBar(false);//当前的值显示在柱体上方还是柱体内部上方


		mLineChart.getDescription().setEnabled(false);//不绘制描述内容
		mLineChart.setMaxVisibleValueCount(60);//超过60后，不显示条目的值
		mLineChart.setPinchZoom(false);//缩放
		mLineChart.setDrawGridBackground(false);//如果为true,绘制网格背景

		//xy轴的数据格式化
		IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mLineChart);
		IAxisValueFormatter custom = new MyAxisValueFormatter();

		XAxis xAxis = mLineChart.getXAxis();
		xAxis.setDrawLabels(true);//显示x轴lable
		xAxis.setLabelCount(6);//x轴显示的lable 数量
		xAxis.setTypeface(mTfLight);
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);
		xAxis.setGranularity(1f); // only intervals of 1 day
		xAxis.setValueFormatter(custom);



		YAxis leftAxis = mLineChart.getAxisLeft();
		leftAxis.setTypeface(mTfLight);
		leftAxis.setLabelCount(6, false);
		leftAxis.setValueFormatter(custom);
		leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
		leftAxis.setSpaceTop(15f);//距离顶部的距离
		leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

		YAxis rightAxis = mLineChart.getAxisRight();
		rightAxis.setDrawGridLines(false);
		rightAxis.setTypeface(mTfLight);
		rightAxis.setLabelCount(6, false);
		rightAxis.setValueFormatter(custom);
		rightAxis.setSpaceTop(15f);
		rightAxis.setAxisMinimum(0f); //最小为0

		Legend l = mLineChart.getLegend();
		//显示在左下方
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
		l.setOrientation(Legend.LegendOrientation.HORIZONTAL);


		l.setDrawInside(false);//是否绘制在图表的外部
		l.setForm(Legend.LegendForm.SQUARE);//图例的外形
		l.setFormSize(9f);//图例的大小
		l.setTextSize(11f);
		l.setXEntrySpace(4f);//图例组之间的间距


		//添加markview
		XYMarkerView xyMarkerView = new XYMarkerView(mContext, xAxisFormatter);
		xyMarkerView.setChartView(mLineChart);
		mLineChart.setMarker(xyMarkerView);

		mLineChart.animateY(1000);
		setData(mLineChart,6, 5);
	}

	private static void setData(BarChart mLineChart,int count, float range) {

		float start = 1f;
		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
		ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
		ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();

		for (int i = (int) start; i < start + count + 1; i++) {
			float mult = (range + 1);
			float val = (float) (Math.random() * mult);
			if (val > 3) {
				yVals1.add(new BarEntry(i, val));
			} else if (val > 1) {
				yVals2.add(new BarEntry(i, val));
			} else {
				yVals3.add(new BarEntry(i, val));
			}


		}

		BarDataSet set1;
		BarDataSet set2;
		BarDataSet set3;


		if (mLineChart.getBarData() != null && mLineChart.getBarData().getDataSetCount() > 0) {
			set1 = (BarDataSet) mLineChart.getData().getDataSetByIndex(0);
			set2 = (BarDataSet) mLineChart.getData().getDataSetByIndex(0);
			set3 = (BarDataSet) mLineChart.getData().getDataSetByIndex(0);
			set1.setValues(yVals1);
			set2.setValues(yVals2);
			set3.setValues(yVals3);
			mLineChart.getData().notifyDataChanged();
			mLineChart.notifyDataSetChanged();

		} else {
			set1 = new BarDataSet(yVals1, "大于30");
			set1.setDrawIcons(false);
			set1.setColors(Color.rgb(192, 255, 140));

			set2 = new BarDataSet(yVals2, "大于10");
			set2.setDrawIcons(false);
			set2.setColors(Color.rgb(255, 247, 140));

			set3 = new BarDataSet(yVals3, "小于10");
			set3.setDrawIcons(false);
			set3.setColors(Color.rgb(255, 208, 140));

			ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
			dataSets.add(set1);
			dataSets.add(set2);
			dataSets.add(set3);

			BarData data = new BarData(dataSets);
			data.setValueTextSize(10f);
			data.setValueTypeface(mTfLight);
			data.setBarWidth(0.9f);

			mLineChart.setData(data);
		}
	}
}
