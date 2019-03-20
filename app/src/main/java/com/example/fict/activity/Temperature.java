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
    String TAG = "TAG";

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
        //Find textview for display last value
        TextView textView = findViewById(R.id.textView5);
        //show date
        TextView textView1 = findViewById(R.id.Date);
        textView1.setText(date.toString());
        //set last value on the main screen
        textView.setText(getTEMPEARTURE());
        //Return array with all value ta a day
        //updateTempGraph();

        new getHis().execute();
    }


    @SuppressLint("StaticFieldLeak")
    class getHis extends AsyncTask<Void, Integer, Void> {
        Respones respones = new Respones();
        Parsing parsing = new Parsing();



        /**
         * A send GET request to the server
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            respones.ResponesHistory("2019-02-14", "2019-03-15", 1, 2);



        }

        // 3. Показать температтуру
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setDayValueHistory(parsing.getDay());

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

    @SuppressLint("ShowToast")

    public void updateTempGraph() {

        // plotting results
        LineChart chart = findViewById(R.id.chart);
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
            entries.add(new Entry((float) i, (float) Math.pow(Math.sin(i), 2) * 25));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Temperature for the last year");
        dataSet.setColor(Color.rgb(0, 255, 0));
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setLineWidth(2.5f);
        dataSet.setCircleRadius(4f);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }
}

