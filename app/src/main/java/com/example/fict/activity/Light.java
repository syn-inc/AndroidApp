package com.example.fict.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fict.MainActivity;
import com.example.fict.R;
import com.example.fict.ValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.fict.MainActivity.getLIGHT;

public class Light extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light);
        TextView textView = findViewById(R.id.textView5);
        Date date = new Date();
        TextView textView1 = findViewById(R.id.Date);
        textView1.setText(date.toString());
        textView.setText(getLIGHT());
        updateLightGraph();
    }
    public void updateLightGraph() {

        // plotting results
        LineChart chart = (LineChart) findViewById(R.id.chart);
        Description desc = chart.getDescription();
        desc.setText("");

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(12);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return MainActivity.getMonths()[(int) value % MainActivity.getMonths().length];
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        List<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 50; i++) {
            entries.add(new Entry((float) i, (float) Math.pow(Math.sin(i), 2)*25));
        }
        LineDataSet dataSet = new LineDataSet(entries,"Quantity of light");
        dataSet.setColor(Color.rgb(0, 255, 0));
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setLineWidth(2.5f);
        dataSet.setCircleRadius(4f);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
      }
    }
