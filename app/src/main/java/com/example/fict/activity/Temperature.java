package com.example.fict.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.fict.MainActivity;
import com.example.fict.MyMarkerView;
import com.example.fict.Parsing;
import com.example.fict.R;
import com.example.fict.Respones;
import com.example.fict.ValueFormatter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.example.fict.MainActivity.getTEMPEARTURE;

public class Temperature extends AppCompatActivity {
    ArrayList<Float> dayValueHistory;
    private LineChart chart;
    public static float minGraphValue = 0;
    public static float maxGraphValue = 0;

    public void setDayValueHistory(ArrayList<Float> dayValueHistory) {
        this.dayValueHistory = dayValueHistory;
    }

    public ArrayList<Float> getDayValueHistory() {
        return dayValueHistory;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
//        Toolbar mToolbar = findViewById(R.id.toolbar);
//        mToolbar.setTitle(getString(R.string.app_name));
//        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
//        ActionBar actionBar = getActionBar();
//        assert actionBar != null;
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Date date = new Date();
        TextView textView = findViewById(R.id.textView5);        //Find textView for display last value
        TextView textView1 = findViewById(R.id.Date);        //show date
        textView1.setText(date.toString());
        textView.setText(getTEMPEARTURE());         //set last value on the main screen
        new getHis().execute(); //Return array with all value ta a day

        createTempGraph();
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


    @SuppressLint("StaticFieldLeak")
    class getHis extends AsyncTask<Void, Integer, Void> {
        Respones respones = new Respones();
        Parsing parsing = new Parsing();

        /**
         * Send GET request to the server
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            respones.ResponesHistory("2019-02-14", "2019-03-15", 1, 2);
        }

        // Showing temperature and updating graph's values
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setDayValueHistory(parsing.getDay());
            updateTempGraph();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            parsing.setRESPONES(respones.getRESPONSES());
            return null;
        }
    }

    /**
     * Creates temperature graph
     */
    public void createTempGraph() {

        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        final int datetime = Integer.parseInt(dateFormat.format(c.getTime()));

        chart = findViewById(R.id.chart);

        Description desc = chart.getDescription(); // initializing and setting a description
        desc.setText("");

        Legend legend = chart.getLegend(); // setting off a legend
        legend.setEnabled(false);

        chart.setNoDataText("No data available for now, sorry about that :c ");
        chart.setBorderWidth(100);

        XAxis xAxis = chart.getXAxis(); // setting size of chart
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            /**
             * @param value float is interpreted as hours with appropriate coefficient
             * @return current value of argument in hours
             */
            @Override
            public String getFormattedValue(float value) {
                return MainActivity.getHours()[(int) (value + datetime) % MainActivity.getHours().length];
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(50f);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawLabels(false);
        chart.invalidate();
    }

    public void getArrayListMinMax(ArrayList<Float> a) {
        float minValue = a.get(0);
        float maxValue = a.get(0);
        for (float i : a) {
            if (i < minValue) {
                minValue = i;
            }
            if (i > maxValue) {
                maxValue = i;
            }
        }
        minGraphValue = minValue - (float) 0.5;
        maxGraphValue = maxValue + (float) 0.5;
    }


    public void updateTempGraph() {

        ArrayList<Float> dayValues = getDayValueHistory();
        getArrayListMinMax(dayValues);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(minGraphValue);
        leftAxis.setAxisMaximum(maxGraphValue);

        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < 25; i++) { // Filling list with x,y, where x is month and y is temp. value;
            entries.add(new Entry((float) i, dayValues.get(dayValues.size() / 25 * i)));
            Log.d("", Float.toString(i));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Temperature for the last year");

        dataSet.setColor(Color.rgb(0, 255, 0)); // adding line parameters
        dataSet.setDrawValues(false);
        dataSet.setLineWidth(2.5f);
        dataSet.setDrawCircles(false);
        //dataSet.setCircleRadius(4f);

        dataSet.setDrawFilled(true);
        dataSet.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return chart.getAxisLeft().getAxisMinimum();
            }
        });
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //to enable the cubic density : if 1 then it will be sharp curve
        dataSet.setCubicIntensity(0.2f);
        dataSet.setFillColor(Color.BLACK);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
        dataSet.setFillDrawable(drawable);

        // set color of filled area
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

        // Set the marker to the chart
        mv.setChartView(chart);
        chart.setMarker(mv);

        LineData lineData = new LineData(dataSet);

        chart.setData(lineData);
        chart.animateY(2000, Easing.EaseInCubic);
        chart.notifyDataSetChanged(); // refreshing chart
        chart.invalidate();
    }
}

