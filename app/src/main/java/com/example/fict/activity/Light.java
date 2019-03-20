package com.example.fict.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fict.R;

import java.util.Date;

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


    }




}
