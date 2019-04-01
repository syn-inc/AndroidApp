package com.example.fict;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fict.activity.Gas;
import com.example.fict.activity.Humidity;
import com.example.fict.activity.Light;
import com.example.fict.activity.Temperature;

import java.util.ArrayList;

/**
 * @author Vlados
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private static final String TAG = "myLogs";
    public Integer TimeSwipeAnimation = 3000;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    ConnectionDetector connectionDetector;



    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImage = new ArrayList<>();




    public TextView MainViewBord; //TextView for represent a data



    private static String TEMPEARTURE;
    private static String LOGDATE;
    private static String MOVE;
    private static String LIGHT;
    private static String HUMIDITY;

    private ImageButton humidity;
    ImageButton light;
    ImageButton gas;
    ImageButton temperature;
    ImageButton fire;
    ImageButton motion;


    public static String[] getMonths() {
        return new String[]{
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
        };
    }

    public static String[] getHours() {
        return new String[]{
                "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
                "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
                "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
    }

    public static String getHUMIDITY() {
        return HUMIDITY;
    }

    public static String getMOVE() {
        return MOVE;
    }

    public static String getTEMPEARTURE() {
        return TEMPEARTURE;
    }
    public static void setTEMPEARTURE(String TEMPEARTURE) {
        MainActivity.TEMPEARTURE = TEMPEARTURE;
    }

    public static String getLIGHT() {
        return LIGHT;
    }
    public static void setHUMIDITY(String HUMIDITY) {
        MainActivity.HUMIDITY = HUMIDITY;
    }

    public static void setLIGHT(String LIGHT) {
        MainActivity.LIGHT = LIGHT;
    }

    //


    //


    /**
     * Called when the activity is first created.
     *
     * @see getRespounes  method provides a request to the server
     * Send a request all the time when we're re-creation Activity too
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Check Internet Connection
        connectionDetector  = new ConnectionDetector(this);
        if(!connectionDetector.isConnected()){
            Toast.makeText(MainActivity.this,"Not Connected",Toast.LENGTH_LONG).show();
        }else {
            new getRespounes().execute();
        }


        SwipeDown();
       // Button_Animation(); // Button animation
        MainViewBord = findViewById(R.id.get);
        MainViewBord.setText(":)");
        initImageBitmaps();



    }



    private void initImageBitmaps(){
        Log.i(TAG, "initImageBitmaps: preparin bitmaps");
        mImage.add(R.drawable.temperature_background);
        mImage.add(R.drawable.huminity_background);
        mImage.add(R.drawable.light_background);
        mImage.add(R.drawable.gas_background);
        mImage.add(R.drawable.motion_background);



        mNames.add("Temperature");
        mNames.add("Huminity");
        mNames.add("Light");
        mNames.add("Gas");
        mNames.add("Motion");

        initRecycleView();

    }

    public void initRecycleView (){
        Log.i(TAG, "initRecycleView: init recycle View");
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(this, mNames, mImage);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
















    /**
     * Animation swipe down provide a reflash data and send a Toast about Connection to the Internet
     * If connection is fail
     *
     */
    public void SwipeDown(){
        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(MainActivity.this);
        //Colors animation a update
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }


    @Override
    public void onRefresh() {
        //Operations are carried out in a new thread
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                //Check connection
                if(connectionDetector.isConnected()){
                    Toast.makeText(MainActivity.this,"Reflash data done",Toast.LENGTH_LONG).show();
                    //Send request if connection is well
                   new getRespounes().execute();
                }else {
                    Toast.makeText(MainActivity.this,"Not Connected",Toast.LENGTH_LONG).show();
                }
                // Cancel animation reflesh
                mSwipeRefreshLayout.setRefreshing(false);
            }

            //Time, how much will be animation
        }, TimeSwipeAnimation);
    }








    /**
     * @param menu creates menu in action bar
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rooms, menu);
        return true;
    }


    /**
     * This method processes the option, which was selected from the room list
     *
     * @param item selected (radio button)
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        item.setChecked(true);

        switch (id) {
            case R.id.room_301:
                Toast.makeText(this, "301 room in development", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.room_302:
                Toast.makeText(this, "302 room in development", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.room_303:
                Toast.makeText(this, "303 room in development", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.room_304:
                Toast.makeText(this, "304 room in development", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.room_305:
                Toast.makeText(this, "305 room in development", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.room_538:
                Toast.makeText(this, "538 room in development", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.room_marik:
                Toast.makeText(this, "Marik room in development", Toast.LENGTH_SHORT).show();
                return true;
        }
        return true;
    }




    /**
     * @see Respones,Parsing the first one, create two new obejets
     * OnPreExecude -
     */

    @SuppressLint("StaticFieldLeak")
    class getRespounes extends AsyncTask<Void, Integer, Void> {
        Respones respones = new Respones();
        Parsing parsing = new Parsing();

        /**
         * Send GET request to the server
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            respones.RequestLastValue(1, 1);



        }

        // 3. Показать температтуру
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setTEMPEARTURE(parsing.getTemperatureLastValue()+"°C");
            setLIGHT(parsing.getLightLastValue()+" lm");
            setHUMIDITY(parsing.getHumidityLastValue()+"%");
           // Respones.methodToast(MainActivity.this);



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


// FIXME: 05.03.2019

    /**
     * Change icons
     * <p>
     * If we're push a on the button like temperature she will has color
     * icon , all buttons will be reset to background (black icons)
     */
    void getDefaultIconsForButtons() {
        humidity.setImageResource(R.drawable.huminity_background);
        light.setImageResource(R.drawable.light_background);
        temperature.setImageResource(R.drawable.temperature_background);
        gas.setImageResource(R.drawable.gas_background);
        fire.setImageResource(R.drawable.fire_background);
        motion.setImageResource(R.drawable.motion_background);
    }


    /**
     * Now checking a push button, and in dependency change
     * 1. background
     * 2. MainViewBord
     * 3. And if will call setOnLongClickListener(), change a activity
     * <p>
     * <p>
     * <p>
     * For real, all OnLongClickListener() must be into another place
     * Maybe create new class, i don't know. It's works!
     */
//    public void Button_Animation() {
//        // Find animation in resources
//        final Animation onClickAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
//
//        humidity = findViewById(R.id.Humidity);
//        light = findViewById(R.id.Light);
//        temperature = findViewById(R.id.Temperature);
//        gas = findViewById(R.id.Gas);
//        fire = findViewById(R.id.Fire);
//        motion = findViewById(R.id.Move);
//
//
///*
//                        Humidity
// */
//        humidity.setOnClickListener(new Button.OnClickListener() {
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(onClickAnimation);
//                getDefaultIconsForButtons();
//                humidity.setImageResource(R.drawable.himinity_color);
//                MainViewBord.setText(getHUMIDITY());
//
//
//            }
//
//        });
//
//        humidity.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Humidity.class);
//                startActivity(intent);
//                return true;
//            }
//        });
//
///*
//                            Light
// */
//        light.setOnClickListener(new Button.OnClickListener() {
//
//
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(onClickAnimation);
//                MainViewBord.setText(getLIGHT());
//                getDefaultIconsForButtons();
//                light.setImageResource(R.drawable.light_color);
//
//
//            }
//        });
//
//        light.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Light.class);
//                startActivity(intent);
//                return true;
//            }
//        });
////                              Gas
////---------------------------------------------------------------------------------------------
//
//        gas.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(onClickAnimation);
//                getDefaultIconsForButtons();
//                gas.setImageResource(R.drawable.gas_color);
//                MainViewBord.setText("Error");
//
//                Toast.makeText(getApplicationContext(), "This sensor is in development", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        gas.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Gas.class);
//                startActivity(intent);
//                return true;
//            }
//        });
//
//
//        //              Temperature
//
//        temperature.setOnClickListener(new Button.OnClickListener() {
//
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(onClickAnimation);
//                MainViewBord.setText(getTEMPEARTURE());
//                getDefaultIconsForButtons();
//                temperature.setImageResource(R.drawable.temperature_color);
//            }
//        });
//        temperature.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Temperature.class);
//                startActivity(intent);
//                return true;
//            }
//        });
////                      Fire
//        fire.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(onClickAnimation);
//                getDefaultIconsForButtons();
//                fire.setImageResource(R.drawable.fire_color);
//                MainViewBord.setText("--");
//
//            }
//        });
//        fire.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Humidity.class);
//                startActivity(intent);
//                return true;
//            }
//        });
////              Motion
//        motion.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(onClickAnimation);
//                getDefaultIconsForButtons();
//                motion.setImageResource(R.drawable.motion_color);
//                MainViewBord.setText("Move");
//
//            }
//        });
//        motion.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Humidity.class);
//                startActivity(intent);
//                return true;
//            }
//        });
//
//    }

}
