package com.callor.movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.movie.databinding.MovieItemViewBinding;
import com.callor.movie.model.MovieDTO;

import java.util.List;

public class NaverMovieAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieDTO> movieList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater
                = LayoutInflater.from(parent.getContext());
        MovieItemViewBinding movieBinding
                = MovieItemViewBinding.inflate(
                        layoutInflater,
                        parent,
                        false
        );
        return new MovieViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public static class MovieViewHolder
            extends RecyclerView.ViewHolder{

        public MovieItemViewBinding movieBiding;
        public MovieViewHolder(@NonNull MovieItemViewBinding movieBiding) {
            super(movieBiding.getRoot());
            this.movieBiding = movieBiding;
        }
    }
}
