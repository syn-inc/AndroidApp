package com.example.fict.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fict.R;

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
    }
}
