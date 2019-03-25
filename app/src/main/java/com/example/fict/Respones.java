package com.example.fict;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Vlados
 * Class created for send Request
 * Set answer from server
 * Get JSON
 */
public class Respones {

    // Just for test
    private static final String TAG = "myLogs";

    //Variable for saved a answer, json
    private String RESPONSES;

    //URL for request
    private String urlLastValue = "http://13.53.149.166/sensorlist.php";
    private String urlHistory = "http://13.53.149.166/sensorhistory.php";


    /*
        There should be three more URL
        1. http://13.53.149.166/roomlist.php  - {"rez":"ok","errormsg":"","data":[{"id":"1","rname":"538","rdesc":null,"bid":"1",
                                               "bname":"18корп"},{"id":"2","rname":"535","rdesc":null,"bid":"1","bname":"18корп"}]}
        2. http://13.53.149.166/buildinglist.php - answer {"rez":"ok","errormsg":"","data":
                                                        [{"bid":"1","bname":"18корп","bdesc":null},{"bid":"2","bname":"1 корп","bdesc":null}]}
        3. http://13.53.149.166/sensorhisory.php - not work now, but return all data about sensor, room , bilding  by date

     */


    /**
     * Only one not private method
     *
     * @return - Json representations
     */
    public String getRESPONSES() {
        return RESPONSES;
    }

    /**
     * @param RESPONSES - set respones.body()string answer in variable
     */
    private void setRESPONSES(String RESPONSES) {
        this.RESPONSES = RESPONSES;
        //Log.d(TAG, "RESPONSES" + RESPONSES);
    }

    /**
     * A send Get request
     *
     * @param bid - building ID, example FICT has id=1
     * @param rid - room ID, example 538 has id = 1, 535 has id=2
     *            I/O exception when connection is gone
     */

    // TODO should we use it?
    @SuppressLint("StaticFieldLeak")
    void Resp(final int bid, final int rid) {
        final AsyncTask connection_error_ = new AsyncTask<Void, Void, ResponseBody>() {

            @Override
            protected ResponseBody doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(urlLastValue + "?bid=" + bid + "&rid=" + rid)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    setRESPONSES(response.body().string());
                } catch (final IOException e) {
                    new Runnable() {
                        @Override
                        public void run() {

                        }
                    };
                } catch (final Exception e) {
                    e.getStackTrace();
                }
                return null;
            }
        }.execute();
    }

    /**
     * @param start - date start
     * @param end   - date end
     * @param id    - id sensor
     * @param step  - step means how much data we need.
     *              Example sensor send date on the one hour, its 24 value
     *              we can get just 3 middle value at a day.
     */
    //http://13.53.149.166/sensorhistory.php?start=2019-03-14&end=2019-03-15&id=1&step=2
    @SuppressLint("StaticFieldLeak")
    public void ResponesHistory(final String start, final String end, final int id, final int step) {
        final AsyncTask connection_error_ = new AsyncTask<Void, Void, ResponseBody>() {

            @Override
            protected ResponseBody doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(urlHistory + "?start=" + start + "&end=" + end + "&id=" + id + "&step=" + step)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    setRESPONSES(response.body().string());

                } catch (final IOException e) {

                    new Runnable() {
                        @Override
                        public void run() {
                        }
                    };
                } catch (final Exception e) {
                    e.getStackTrace();
                }
                return null;
            }

        }.execute();
    }
}
