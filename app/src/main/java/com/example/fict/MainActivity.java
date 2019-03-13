package com.example.fict;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fict.activity.Gas;
import com.example.fict.activity.Humidity;
import com.example.fict.activity.Light;
import com.example.fict.activity.Temperature;

/**
 * @author Vlados
 */
public class MainActivity extends AppCompatActivity {


    private static final String TAG = "myLogs";

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


    public static String getHUMIDITY() {
        return HUMIDITY;
    }

    public static String getMOVE() {
        return MOVE;
    }

    public static String getTEMPEARTURE() {
        return TEMPEARTURE;
    }

    public static String getLIGHT() {
        return LIGHT;
    }

    public static String getLOGDATE() {
        return LOGDATE;
    }

    public static void setHUMIDITY(String HUMIDITY) {
        MainActivity.HUMIDITY = HUMIDITY;
    }

    public static void setMOVE(String MOVE) {
        MainActivity.MOVE = MOVE;
    }

    public static void setTEMPEARTURE(String TEMPEARTURE) {
        MainActivity.TEMPEARTURE = TEMPEARTURE;
    }

    public static void setLOGDATE(String LOGDATE) {
        MainActivity.LOGDATE = LOGDATE;
    }

    public static void setLIGHT(String LIGHT) {
        MainActivity.LIGHT = LIGHT;
    }


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
        new getRespounes().execute();

        Button_Animation(); // Button animation


        MainViewBord = findViewById(R.id.get);
        MainViewBord.setText(":)");
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
         * A send GET request to the server
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            respones.Resp(1, 1);


        }

        // 3. Показать температтуру
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setTEMPEARTURE(parsing.getTemperatureLastValue());
            setLIGHT(parsing.getLightLastValue());
            setHUMIDITY(parsing.getHumidityLastValue());

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
    void getDefoltIconsForButtons() {
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
    public void Button_Animation() {
        // Find animation in resourses
        final Animation onClickAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);

        humidity = findViewById(R.id.Humidity);
        light = findViewById(R.id.Light);
        temperature = findViewById(R.id.Temperature);
        gas = findViewById(R.id.Gas);
        fire = findViewById(R.id.Fire);
        motion = findViewById(R.id.Move);


/*
                        Humidity
 */
        humidity.setOnClickListener(new Button.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                view.startAnimation(onClickAnimation);
                getDefoltIconsForButtons();
                humidity.setImageResource(R.drawable.himinity_color);
                MainViewBord.setText(getHUMIDITY());


            }

        });

        humidity.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, Humidity.class);
                startActivity(intent);
                return true;
            }
        });

/*
                            Light
 */
        light.setOnClickListener(new Button.OnClickListener() {


            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                view.startAnimation(onClickAnimation);
                MainViewBord.setText(getLIGHT());
                getDefoltIconsForButtons();
                light.setImageResource(R.drawable.light_color);


            }
        });

        light.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, Light.class);
                startActivity(intent);
                return true;
            }
        });
//                              Gas
//---------------------------------------------------------------------------------------------

        gas.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(onClickAnimation);
                getDefoltIconsForButtons();
                gas.setImageResource(R.drawable.gas_color);
                MainViewBord.setText("Error");

                Toast.makeText(getApplicationContext(), "This sensor is in development", Toast.LENGTH_SHORT).show();

            }
        });
        gas.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, Gas.class);
                startActivity(intent);
                return true;
            }
        });


        //              Temperature

        temperature.setOnClickListener(new Button.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                view.startAnimation(onClickAnimation);
                MainViewBord.setText(getTEMPEARTURE());
                getDefoltIconsForButtons();
                temperature.setImageResource(R.drawable.temperature_color);


            }
        });
        temperature.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, Temperature.class);
                startActivity(intent);
                return true;
            }
        });
//                      Fire
        fire.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(onClickAnimation);
                getDefoltIconsForButtons();
                fire.setImageResource(R.drawable.fire_color);
                MainViewBord.setText("--");

            }
        });
        fire.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, Humidity.class);
                startActivity(intent);
                return true;
            }
        });
//              Motion
        motion.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(onClickAnimation);
                getDefoltIconsForButtons();
                motion.setImageResource(R.drawable.motion_color);
                MainViewBord.setText("Move");

            }
        });
        motion.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, Humidity.class);
                startActivity(intent);
                return true;
            }
        });

    }
}
