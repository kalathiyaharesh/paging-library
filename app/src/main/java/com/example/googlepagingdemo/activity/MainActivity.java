package com.example.googlepagingdemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.googlepagingdemo.R;
import com.example.googlepagingdemo.adapter.MovieDataAdapter;
import com.example.googlepagingdemo.constant.ConstApi;
import com.example.googlepagingdemo.model.MovieModel;
import com.example.googlepagingdemo.viewmodel.MovieViewModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    MovieViewModel viewModel;
    RecyclerView recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        viewModel.itemPagedList.observe(this, new Observer<PagedList<MovieModel.Result>>() {
            @Override
            public void onChanged(PagedList<MovieModel.Result> results) {
                MovieDataAdapter photosAdapter = new MovieDataAdapter(MainActivity.this);
                photosAdapter.submitList(results);
                recyclerview.setAdapter(photosAdapter);
            }
        });
    }
}