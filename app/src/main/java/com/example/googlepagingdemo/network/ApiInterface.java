package com.example.googlepagingdemo.network;

import com.example.googlepagingdemo.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/movie/popular")
    Call<MovieModel> doGetListResources(@Query("page") int page,@Query("pageSize") int pageSize);
}
