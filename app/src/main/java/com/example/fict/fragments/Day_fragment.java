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

        return inflater.inflate(R.layout.fragment_day, container, false);



    }
}
