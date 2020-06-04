package com.example.androidchart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {
    private int DATA_COUNT = 5;
    BarChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Bar Chart");

        chart = (BarChart)findViewById(R.id.chart_bar);


        settingYAxis();
        settingXAxis();
        setChartValue();
        settingChart();
    }

    private void settingChart() {
        chart.getDescription().setEnabled(false);
        chart.setMaxHighlightDistance(100);
        chart.setFitBars(true);
        chart.getData().setHighlightEnabled(false);


        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
        chart.getAxisLeft().setDrawGridLines(false);
        // add a nice and smooth animation
        chart.animateY(1500);
        chart.getLegend().setEnabled(false);
    }

    private void settingXAxis() {
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Today");
        xAxisLabel.add("Target");

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xAxisLabel.get((int) value);
            }
        };
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
    }

    private void settingYAxis() {
        YAxis y = chart.getAxisLeft();
        y.setLabelCount(5);
        y.setAxisMinimum(0);
        y.setAxisMaximum(200);

        y = chart.getAxisRight();
        y.setLabelCount(5);
        y.setAxisMinimum(0);
        y.setAxisMaximum(200);
    }

    private void setChartValue() {
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(0, 100));
        values.add(new BarEntry(1, 200));

        BarDataSet set1;

        set1 = new BarDataSet(values, "Data Set");
        set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set1.setDrawValues(false);
        set1.setBarBorderWidth(1.0f);
        set1.setDrawValues(true);
        set1.setValueTextSize(20f);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        chart.setData(data);
        chart.setFitBars(false);

        chart.invalidate();

    }


}
