package com.example.googlepagingdemo.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.googlepagingdemo.R;
import com.example.googlepagingdemo.constant.ArticleMovieConstants;
import com.example.googlepagingdemo.model.MovieModel;

import org.jetbrains.annotations.NotNull;

public class MovieDataAdapter extends PagedListAdapter<MovieModel.Result, MovieDataAdapter.MovieHolder> {
    Context context;

    public MovieDataAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_movie,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieHolder holder, int position) {
        holder.title.setText(String.valueOf(getItem(position).getOriginalTitle()));
        Glide.with(context).load(ArticleMovieConstants.ImageUrl+getItem(position).getPosterPath()).into(holder.image);
    }

    private static DiffUtil.ItemCallback<MovieModel.Result> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieModel.Result>() {
                @Override
                public boolean areItemsTheSame(MovieModel.Result oldArticle, MovieModel.Result newArticle) {
                    return oldArticle.getTitle() == newArticle.getTitle();
                }

                @Override
                public boolean areContentsTheSame(MovieModel.Result oldArticle, MovieModel.Result newArticle) {
                    return oldArticle.equals(newArticle);
                }
            };


    public class MovieHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        public MovieHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            image = itemView.findViewById(R.id.image);
        }
    }
}
