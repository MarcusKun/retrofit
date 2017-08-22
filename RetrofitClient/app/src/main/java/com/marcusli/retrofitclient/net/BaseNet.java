package com.marcusli.retrofitclient.net;

import com.marcusli.retrofitclient.ConstantValue;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 59821 on 2017/8/16.
 */

public class BaseNet {
    protected Retrofit retrofit;
    public BaseNet(){
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantValue.BaseUrl + "/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
