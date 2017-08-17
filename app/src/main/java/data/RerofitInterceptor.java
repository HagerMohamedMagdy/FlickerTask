package data;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Hager.Magdy on 8/16/2017.
 */

public class RerofitInterceptor implements Interceptor {
    Map<String, String> retrofitheaders;

    Context mContext;
    public RerofitInterceptor(Map<String, String> mretrofitheaders) {
        this.retrofitheaders = mretrofitheaders;

    }
    @Override
    public Response intercept(Chain chain) throws IOException {


        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        if (retrofitheaders != null) {

            retrofitheaders.put("gallery_id", "6065-72157617483228192");
         //   retrofitheaders.put("api_key", "7fd5698c18708b3056269cea55e52271");
           // retrofitheaders.put("format", "json");
           /// retrofitheaders.put("nojsoncallback", "1");

            // Fill in the Form parameters
            for (String key : retrofitheaders.keySet()) {
                Object value = retrofitheaders.get(key);
                if (value != null) {
                    builder.addHeader(key, value.toString());
                    Log.e("mHeaders", key + ":" + value.toString());
                } else{

                    retrofitheaders.put(key, null);}
            }
        }

        Request request = builder.build();
        Response response = chain.proceed(request);
        return response;
    }
}
