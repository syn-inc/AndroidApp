package com.example.fict.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fict.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;

import static com.example.fict.MainActivity.getTEMPEARTURE;

public class Temperature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        TextView textView = findViewById(R.id.textView5);
        Date date = new Date();
        TextView textView1 = findViewById(R.id.Date);
        textView1.setText(date.toString());
        textView.setText(getTEMPEARTURE());
        updateTempGraph();
    }

    @SuppressLint("ShowToast")

    public void updateTempGraph() {

        // plotting results
        GraphView graph = findViewById(R.id.graph_temperature);
        initGraph(graph);
    }

    public void initGraph(GraphView graph) {

        DataPoint[] points = new DataPoint[30];
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(i, i);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        series.setColor(Color.argb(255, 0, 255, 0));
        series.setTitle("Temperature");

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(7);

        graph.getViewport().setScrollable(true);
        graph.addSeries(series);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }

//    public void MonthButton(View view){
//        Toast.makeText(this, "btn clicked", 1).show();
//        GraphView graph = findViewById(R.id.graph_temperature);
//        graph.getViewport().setYAxisBoundsManual(true);
//        graph.getViewport().setMinY(0);
//        graph.getViewport().setMaxY(10);
//        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setMinX(0);
//        graph.getViewport().setMaxX(10);
//    }
}
