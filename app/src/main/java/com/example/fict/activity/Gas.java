package com.example.fict.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.fict.R;
import com.example.fict.fragments.Day_fragment;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Gas extends AppCompatActivity {
    Day_fragment day_fragment;

    Button day, week, mouth, year;
    Fragment frag1;
    // FragmentTransaction fTrans;       // 413 commented it

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);
        day = findViewById(R.id.day);
        day_fragment = new Day_fragment();
        updateGasGraph();
    }

    public void updateGasGraph() {

        // plotting results
        GraphView graph = findViewById(R.id.graph_gas);
        initGraph(graph);
    }

    public void initGraph(GraphView graph) {

        DataPoint[] points = new DataPoint[30];
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(i, Math.sin(i));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        series.setColor(Color.argb(255, 0, 255, 0));
        series.setTitle("Gas");

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(7);

        graph.getViewport().setScrollable(true);
        graph.addSeries(series);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }


}
