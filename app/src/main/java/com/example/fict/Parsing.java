package com.example.fict;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * We must pass to the constructor  - json
 */
public class Parsing {

    private String RESPONSES;
    //private static final String TAG = "myLogs";
    private int id;
    private String value;

    public void setRESPONSES(String RESPONSES) {
        this.RESPONSES = RESPONSES;
    }

    private void setValue(String value) {
        this.value = value;
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }


    /**
     * @return - light sensor value
     */

    String getLightLastValue() {
        getLastValueSensor(6);
        //Log.d(TAG, "ValueLight = " + value);
        return value;
    }

    /**
     * @return - temperature sensor value
     */
    String getTemperatureLastValue() {
        getLastValueSensor(1);
        return value;
    }

    /**
     * @return - humidity sensor value
     */
    String getHumidityLastValue() {
        getLastValueSensor(2);
        return value;
    }

    /**
     * Simple parsing Because already the following sensors
     * will be placed in other classrooms and will need to parse request roomlist
     * and  sensorlist to correctly display data.
     * <p>
     * Create a JSONobject and put in paramerts RESPONSES
     * Than get JSONarray depending on which id, we iterate over the array
     * where the value of the key is equal to the parameter id and we get
     * the value of the 'logvalue' in the element and set to 'value'
     *
     * @param id = sensor ID in database
     */
    private void getLastValueSensor(int id) {
        if (RESPONSES != null) {
            try {
                JSONObject jsonObject = new JSONObject(RESPONSES);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i <= jsonArray.length() + 1; i++) {
                    JSONObject jsonObject1 = new JSONObject(jsonArray.getString(i));
                    setId(jsonObject1.getInt("id"));
                    //Log.d(TAG, "ID on the iteration " + i + " = " + getId());
                    if (getId() == id) {
                        setValue(jsonObject1.getString("logvalue"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            setValue(":)");
        }
    }

    /**
     * @return array
     */
    public ArrayList<Float> getDay () {

        ArrayList<Float> array = new ArrayList<>();
        Float iterator = null;
        if (RESPONSES != null) {
            try {
                JSONObject jsonObject = new JSONObject(RESPONSES);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i <jsonArray.length() ; i++) {
                    String jsonArray1 = jsonArray.getString(i);
                    for (int j = 0; j < jsonArray1.length() ; j++) {
                        int index1 = jsonArray1.indexOf(',')+1;
                        iterator = Float.parseFloat(jsonArray1.substring(index1,jsonArray1.length()-1));
                    }
                    array.add(iterator);
                }
                //array.remove(0);
                //Log.d(TAG, String.valueOf(array));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            return null;
        }
        return array;
    }
}
