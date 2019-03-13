package com.example.fict.activity;

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

import static com.example.fict.MainActivity.getHUMIDITY;

public class Humidity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.humidity);
        Date date = new Date();
        TextView textView = findViewById(R.id.Date);
        textView.setText(date.toString());
        TextView textView1 = findViewById(R.id.textView5);
        textView1.setText(getHUMIDITY());
        updateHumGraph();
    }

    public void updateHumGraph() {

        // plotting results
        GraphView graph = findViewById(R.id.graph_humidity);
        initGraph(graph);
    }

    public void initGraph(GraphView graph) {

        DataPoint[] points = new DataPoint[30];
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(i, Math.sin(i));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        series.setColor(Color.argb(255, 0, 255, 0));
        series.setTitle("Light");

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(7);

        graph.getViewport().setScrollable(true);
        graph.addSeries(series);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }
}
