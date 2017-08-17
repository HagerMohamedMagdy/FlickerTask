package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Hager.Magdy on 8/16/2017.
 */

public interface RetrofitService {


    OkHttpClient client = Constants.httpClient.readTimeout(90000, TimeUnit.MILLISECONDS).connectTimeout(90000, TimeUnit.MILLISECONDS).build();
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.FLICKR_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    //post methode to send parameters in body
    @FormUrlEncoded
    @POST
    Call<JsonObject> postMethode(@Url String url,

                                 @FieldMap Map<String, String> options);
    @GET
    Call<JsonObject> getMethode(@Url String url);

}
