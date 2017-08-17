package com.example.hagermagdy.androidflickerdemo.controller;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hagermagdy.androidflickerdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapters.RecyclerViewAdapter;
import data.APIListener;
import data.Constants;
import data.RerofitInterceptor;
import data.RetrofitAsynTask;
import data.Utility;
import models.animal;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements APIListener {
    Map<String, String> mRetrofitHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }
    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<animal> animalList = getaAnimalList("6065-72157617483228192");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(),animalList);
        recyclerView.setAdapter(adapter);

    }
    //retrive data from webservice and fill the current list
    private ArrayList<animal> getaAnimalList(String gallery_id){

        ArrayList<animal> animal_list = new ArrayList<>();
        Utility.showProgressDialog(MainActivity.this, getString(R.string.Loading));
        generateRetrofitHttpHeader(this);
        Constants.httpClient = new OkHttpClient.Builder();


        Constants.httpClient.addInterceptor(new RerofitInterceptor(mRetrofitHeader));
        new RetrofitAsynTask(0,Constants.GET_PHOTOS, Constants.METHOD_GET,mRetrofitHeader, null
                , this, this).execute();
//        for(int i=0;i<animal.length;i++){
//            AndroidVersion androidVersion = new AndroidVersion();
//            androidVersion.setAndroid_version_name(android_version_names[i]);
//            androidVersion.setAndroid_image_url(android_image_urls[i]);
//            android_version.add(androidVersion);
//        }
        return animal_list;
    }











    public void generateRetrofitHttpHeader(Activity mActivity) {
        mRetrofitHeader = new HashMap<>();


        mRetrofitHeader.put("gallery_id", "6065-72157617483228192");




    }

    @Override
    public void onSuccess(int id, String url, String response) {

            Utility.removeProgressDialog();
    }

    @Override
    public void onFailure(int id, String url, String response, int responseCode) {

            Utility.removeProgressDialog();
    }
}
