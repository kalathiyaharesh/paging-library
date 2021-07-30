package com.example.googlepagingdemo.model;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class MoviewDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, MovieModel.Result>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, MovieModel.Result> create() {
        //getting our data source object
        MovieDataSource articleDataSource = new MovieDataSource();

        //posting the data source to get the values
        itemLiveDataSource.postValue(articleDataSource);

        //returning the data source
        return articleDataSource;
    }

    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, MovieModel.Result>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
