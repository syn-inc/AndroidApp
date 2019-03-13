package com.example.fict;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/**
 *
 * @author Vlados
 * Class created for send Request
 * Set answer from server
 * Get JSON
 */
class Respones {


    // Just for test
    private static final String TAG = "myLogs";

    //Variable for saved a answer, json
    private String RESPONSES;

    //URL for request
    private String urlLastValue = "http://13.53.149.166/sensorlist.php?id=1&rid=1";


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
     * @return - Json representations
     */
    String getRESPONSES() {
        return RESPONSES;

    }


    /**
     * @param RESPONSES - set respones.body()string answer in variable
     *
     */
    private void setRESPONSES(String RESPONSES) {
        this.RESPONSES = RESPONSES;
        Log.d(TAG,"RESPONSES" + RESPONSES);
    }


    /**
     * A send Get request
     * @param bid - building ID, example FICT has id=1
     * @param rid  - room ID, example 538 has id = 1, 535 has id=2
     * I/O exception when connection is gone
     */
    @SuppressLint("StaticFieldLeak")
    void Resp(final int bid, final int rid ) {
        final AsyncTask connection_error_ = new AsyncTask<Void, Void, ResponseBody>() {

            @Override
            protected ResponseBody doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url(urlLastValue +"?bid="+bid+"&rid="+rid)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    assert response.body() != null;
                    setRESPONSES(response.body().string());


                } catch (final IOException e) {
                    new Runnable() {
                        @Override
                        public void run() {
                            Log.e(urlLastValue, "Connection Error ", e);
                        }
                    };
                } catch (final Exception e) {
                    e.getStackTrace();
                }
                return  null;
            }

        }.execute();

    }
}
