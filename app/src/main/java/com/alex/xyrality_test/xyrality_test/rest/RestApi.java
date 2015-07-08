package com.alex.xyrality_test.xyrality_test.rest;


import com.alex.xyrality_test.xyrality_test.rest.results.WorldsResult;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("/wa/worlds")
    void getWorlds(@Field("login") String login, @Field("password") String password,
                   @Field("deviceType") String deviceType, @Field("deviceId") String deviceId,
                   Callback<WorldsResult> callback);

}