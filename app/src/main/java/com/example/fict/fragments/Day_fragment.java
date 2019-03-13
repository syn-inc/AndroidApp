package com.example.fict.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fict.R;


public class Day_fragment extends Fragment  {

    EditText textActivity;
    Button buttonSendToFragment;
    Fragment myFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        lineChart.setDragEnabled(true);
//        lineChart.setScaleEnabled(false);
//        ArrayList<Entry> yValue = new ArrayList<>();
//        yValue.add(new Entry(0,60f));
//        yValue.add(new Entry(1,50f));
//        yValue.add(new Entry(2,40f));
//        yValue.add(new Entry(3,60f));
//        yValue.add(new Entry(4,30f));
//        yValue.add(new Entry(5,90f));
//        yValue.add(new Entry(6,50f));
//
//        LineDataSet set1 = new LineDataSet(yValue, "Data set 1");
//
//
//        set1.setFillAlpha(110);
//
//
//
//
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//
//
//        dataSets.add(set1);
//
//        LineData data = new LineData(dataSets);
//
//        lineChart.setData(data);
//        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_day, container, false);



    }
}
