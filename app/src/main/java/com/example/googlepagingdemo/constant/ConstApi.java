package com.example.googlepagingdemo.constant;

import com.example.googlepagingdemo.network.ApiInterface;
import com.example.googlepagingdemo.network.RetrofitClient;

public class ConstApi {

    public static ApiInterface apiInterface;

    public static ApiInterface apiInterface(){
        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        return apiInterface;
    }
}
