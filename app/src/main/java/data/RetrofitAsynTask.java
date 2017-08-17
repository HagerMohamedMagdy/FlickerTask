package data;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hager.Magdy on 8/16/2017.
 */

public class RetrofitAsynTask extends AsyncTask<String, Integer, Object> {
    Map<String, String> mRetrofitHeader;
    Call<JsonObject> call;
    APIListener mListener;
    String mUri;
    String mMethod;
    int mID;
    Map<String, String> options;
    private Context context;
    String mResponseBody;
    public RetrofitAsynTask(int pID, String pUri, String pMethod, Map<String, String> pHeaders,
                            Map<String, String> options, APIListener pListener, Context context) {
        Log.e("Retrofit constructor","herer");

        mRetrofitHeader = pHeaders;
        mListener = pListener;
        mUri =  pUri;
        mMethod = pMethod;
        mID = pID;
        this.options = options;
        this.context = context;
        call = null;
    }

    @Override
    protected Object doInBackground(String... params) {
        RetrofitService mRetrofitService = RetrofitService.retrofit.create(RetrofitService.class);
        if (mMethod.equalsIgnoreCase(Constants.METHOD_GET)) {
            call = mRetrofitService.getMethode(mUri);
        } else if (options != null) {

            Map<String, String> data = new HashMap<>();
            for (String key : options.keySet()) {
                Object value = options.get(key);
                if (value != null) {

                    data.put(key, value.toString());
                }
                call = mRetrofitService.postMethode(mUri, data);
            }


        } else {
            return -1;
        }

        return null;
    }
    @Override
    protected void onPostExecute(Object o) {
        Utility.removeProgressDialog();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //   final TextView textView = (TextView) findViewById(R.id.textView);
                Log.e("On response",response.code()+"reached");

                if (response.isSuccessful()) {

                    mResponseBody = response.body().toString();




                    JSONObject resultObject = null;

                    try {
                        resultObject = new JSONObject(mResponseBody);
                        Log.e("REsopnse Found","HEREE");
                       // String mResult = resultObject.getString(Constants.RESULT);
                       // mListener.onSuccess(mID, mUri, mResponseBody);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (response.code() == 401) {
                    Log.e(" @@", "555" + response.code());
                  //  mListener.onFailure(mID, mUri, mResponseBody, mResponseCode);
                    // Handle unauthorized
                } else {
                 //   mListener.onFailure(mID, mUri, mResponseBody, mResponseCode);
                    // Handle other responses
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("Fail","FOund");
                mResponseBody = t.getMessage();

             //   mListener.onFailure(mID, mUri, mResponseBody, mResponseCode);
                // Handle unauthorized
            }
        });
    }
}
