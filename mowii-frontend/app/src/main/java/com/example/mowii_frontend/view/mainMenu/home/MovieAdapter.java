package com.example.mowii_frontend.view.mainMenu.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mowii_frontend.R;
import com.example.mowii_frontend.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Context context;
    private final ArrayList<Movie> items;

    public MovieAdapter(Context context, ArrayList<Movie> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies_row, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentItem = items.get(position);
        holder.bind(currentItem);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        TextView movieDirector;
        TextView movieYear;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.txt_movieName);
            movieYear = itemView.findViewById(R.id.txtReleaseYear);
        }

        public void bind(Movie movie) {
            movieName.setText(movie.getTitle());
            movieYear.setText(String.valueOf(movie.getReleaseYear()));
        }
    }
}
