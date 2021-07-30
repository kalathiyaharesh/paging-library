package com.example.googlepagingdemo.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.googlepagingdemo.constant.ArticleMovieConstants;
import com.example.googlepagingdemo.model.MovieDataSource;
import com.example.googlepagingdemo.model.MovieModel;
import com.example.googlepagingdemo.model.MoviewDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieViewModel extends ViewModel {
    public LiveData<PagedList<MovieModel.Result>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, MovieModel.Result>> liveDataSource;

    public MovieViewModel() {
        Log.d("TAG", "ArticleViewModel called");

        MoviewDataSourceFactory articleDataSourceFactory = new MoviewDataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = articleDataSourceFactory.getItemLiveDataSource();

        // Getting PagedList configuration
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setPageSize(ArticleMovieConstants.PAGE_SIZE).build();

        // Building the paged list
        itemPagedList = (new LivePagedListBuilder(articleDataSourceFactory, pagedListConfig)).build();
    }
}
