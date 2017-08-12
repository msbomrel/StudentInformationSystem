package com.example.msbomrel.studentinformationsystem.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class Retroserver {
    private  static  final String base_url =  "https://mohanbomrel.000webhostapp.com/crud/";
    private static Retrofit retrofit;
    public static Retrofit getClient()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
