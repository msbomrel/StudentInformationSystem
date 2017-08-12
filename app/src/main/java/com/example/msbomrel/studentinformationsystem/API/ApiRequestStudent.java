package com.example.msbomrel.studentinformationsystem.API;


import com.example.msbomrel.studentinformationsystem.Model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by msbomrel on 4/16/2017.
 */

public interface ApiRequestStudent {

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendBiodata(
            @Field("name") String name,
            @Field("batch") String batch,
            @Field("email") String email,
            @Field("mobile") String mobile);

    @GET("read.php")
    Call<ResponsModel> getBiodata();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponsModel> updateData(
            @Field("id") String id,
            @Field("name") String name,
            @Field("batch") String age,
            @Field("email") String email,
            @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponsModel> deleteData(@Field("id") String id);
}
