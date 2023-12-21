package com.example.mowii_frontend.view.mainMenu.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mowii_frontend.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    ImageView imgMovie;
    TextView movieName, movieDirector, movieYear;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieName = itemView.findViewById(R.id.txt_movieName);
        movieYear = itemView.findViewById(R.id.txtReleaseYear);
    }
}
