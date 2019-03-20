package com.example.fict.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fict.Parsing;
import com.example.fict.R;
import com.example.fict.Respones;
import com.github.mikephil.charting.charts.LineChart;


import java.util.Date;

import static com.example.fict.MainActivity.getTEMPEARTURE;

public class Temperature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        //Create a new request
        Respones respones = new Respones();
        Parsing parsing = new Parsing();
        Date date = new Date();

       //Find textview for display last value
        TextView textView = findViewById(R.id.textView5);
        //show date
        TextView textView1 = findViewById(R.id.Date);
        textView1.setText(date.toString());
        //set last value on the main screen
        textView.setText(getTEMPEARTURE());
        respones.ResponesHistory("2019-02-14","2019-03-15",1,2);
        //Returne array with all value ta a day
        parsing.getDay();




    }

}
