package com.example.mowii_frontend;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    ImageView imgMovie;
    TextView movieName, movieDirector, movieYear;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieName = itemView.findViewById(R.id.movieName);
        movieDirector = itemView.findViewById(R.id.movieDirector);
        movieYear = itemView.findViewById(R.id.movieYear);
    }
}
