package com.alex.xyrality_test.xyrality_test.rest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public enum RestClient {

    INSTANCE;

    private final RestApi mRestApi;

    private final static String baseUrl = "http://backend1.lordsandknights.com/XYRALITY/WebObjects/BKLoginServer.woa";

    RestClient() {
        OkClient okClient = new OkClient(new OkHttpClient());
        Gson gson = new GsonBuilder()
                .create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(okClient)
                .setEndpoint(baseUrl)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Accept", "application/json");
                    }
                })
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String s) {
                        Log.d("RestClient", s);
                    }
                })
                .build();
        mRestApi = restAdapter.create(RestApi.class);
    }

    public RestApi getRestApi() {
        return mRestApi;
    }
}
