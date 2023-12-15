package com.example.mowii_frontend;

import android.content.ClipData;
import android.content.Context;
import android.media.RouteListingPreference;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    Context context;
    List<Movie> items;

    public MovieAdapter(Context context, List<Movie> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.movies_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movieName.setText(items.get(position).getMovieName());
        holder.movieDirector.setText(items.get(position).getDirector());
        holder.movieYear.setText(items.get(position).getReleaseYear());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
