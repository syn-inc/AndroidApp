package com.example.fict.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fict.Parsing;
import com.example.fict.R;
import com.example.fict.ValueFormatter;
import com.example.fict.MainActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.example.fict.Respones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.fict.MainActivity.getTEMPEARTURE;

public class Temperature extends AppCompatActivity {
    ArrayList dayValueHistory;

    public void setDayValueHistory(ArrayList dayValueHistory) {
        this.dayValueHistory = dayValueHistory;
    }

    public ArrayList getDayValueHistory() {
        return dayValueHistory;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        Date date = new Date();
        TextView textView = findViewById(R.id.textView5);        //Find textview for display last value
        TextView textView1 = findViewById(R.id.Date);        //show date
        textView1.setText(date.toString());
        textView.setText(getTEMPEARTURE());         //set last value on the main screen
        new getHis().execute(); //Return array with all value ta a day

        createTempGraph();
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
        LineChart chart = findViewById(R.id.chart);

        Description desc = chart.getDescription(); // initializing and setting a description
        desc.setText("");

        Legend legend = chart.getLegend(); // setting off a legend
        legend.setEnabled(false);

        XAxis xAxis = chart.getXAxis(); // setting size of chart
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(24);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            /**
             * @param value float is interpreted as hours with appropriate coefficient
             * @return current value of argument in hours
             */
            @Override
            public String getFormattedValue(float value) {
                return MainActivity.getHours()[(int) value % MainActivity.getHours().length];
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(50f);
        chart.invalidate();
    }

    public void updateTempGraph() {

        ArrayList test = getDayValueHistory();

        LineChart chart = findViewById(R.id.chart); // plotting results
        List<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < test.size(); i++) { // Filling list with x,y, where x is month and y is temp. value
            entries.add(new Entry((float) i, (float) test.get(i)));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Temperature for the last year");

        dataSet.setColor(Color.rgb(0, 255, 0)); // adding line parameters
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setLineWidth(2.5f);
        dataSet.setCircleRadius(4f);

        LineData lineData = new LineData(dataSet);

        chart.setData(lineData);
        chart.invalidate(); // refreshing chart
    }
}

