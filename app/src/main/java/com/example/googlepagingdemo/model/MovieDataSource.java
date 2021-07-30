package com.example.googlepagingdemo.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.googlepagingdemo.constant.ArticleMovieConstants;
import com.example.googlepagingdemo.constant.ConstApi;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, MovieModel.Result> {
    @Override
    public void loadInitial(@NonNull @NotNull LoadInitialParams<Integer> params, @NonNull @NotNull LoadInitialCallback<Integer, MovieModel.Result> callback) {

        Call<MovieModel> call = ConstApi.apiInterface().doGetListResources(ArticleMovieConstants.FIRST_PAGE,ArticleMovieConstants.PAGE_SIZE);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                if (response.body() != null) {
                    callback.onResult(response.body().getResults(), null, ArticleMovieConstants.FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.d("responseFail",t.getMessage());
            }
        });

    }

    @Override
    public void loadBefore(@NonNull @NotNull LoadParams<Integer> params, @NonNull @NotNull LoadCallback<Integer, MovieModel.Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull @NotNull LoadParams<Integer> params, @NonNull @NotNull LoadCallback<Integer, MovieModel.Result> callback) {
        Call<MovieModel> call = ConstApi.apiInterface().doGetListResources(params.key,ArticleMovieConstants.PAGE_SIZE);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                Log.d("responseSuccess",new Gson().toJson(response.body()));

                if (response.body() != null) {


                    Log.d("TAG", "params Key :: " + params.key + " params load size - " + params.requestedLoadSize);

                    //if the response getTotalResults is not null then
                    //incrementing the next page number
                    Integer key = (params.key == response.body().getTotalResults()) ? null : params.key + 1;

                    callback.onResult(response.body().getResults(), key);
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.d("responseFail",t.getMessage());
            }
        });
    }
}
