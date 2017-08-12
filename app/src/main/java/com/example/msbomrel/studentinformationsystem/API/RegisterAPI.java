package com.example.msbomrel.studentinformationsystem.API;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by msbomrel on 8/9/17.
 */

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/register.php")
    public void insertUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            Callback<Response> callback);
}

