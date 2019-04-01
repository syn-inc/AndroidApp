package com.example.fict;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fict.activity.Humidity;
import com.example.fict.activity.Light;
import com.example.fict.activity.Temperature;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    public static final String TAG = "RecycleViewAdapter";
    private ArrayList<String> mImageName;
    private ArrayList<Integer> mImage;
    private Context context;

    public RecycleViewAdapter( Context context,ArrayList<String> mImageName, ArrayList<Integer> mImage) {
        this.mImageName = mImageName;
        this.mImage = mImage;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.actibnivn, viewGroup,false);
        ViewHolder viewHolder =  new ViewHolder(view);

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final int position_id =  mImage.get(i);
        final String positionName = mImageName.get(i);
        viewHolder.imageView.setImageResource(position_id);
        viewHolder.imagename.setText(positionName);
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (positionName){
                    case "Temperature":
                         Intent temperatureIntent = new Intent(context, Temperature.class);
                        context.startActivity(temperatureIntent);
                        break;
                    case "Huminity":
                         Intent humidityIntent = new Intent(context, Humidity.class);
                        context.startActivity(humidityIntent);
                        break;
                    case "Light":
                        Intent lightIntent = new Intent(context, Light.class);
                        context.startActivity(lightIntent);
                        break;

                }


                Toast.makeText(context,"You onclick"+positionName,Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mImageName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView imagename;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageExample);
            imagename  = itemView.findViewById(R.id.description);
            parentLayout = itemView.findViewById(R.id.relativeLayout);

        }
    }
}
