package com.example.mowii_frontend.view.home.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mowii_frontend.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_details);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(""); // or use null
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        if (intent != null) {
            String movieId = intent.getStringExtra("MOVIE_ID");
            String movieTitle = intent.getStringExtra("MOVIE_TITLE");
            String movieDirector = intent.getStringExtra("MOVIE_DIRECTOR");
            String moviePoster = intent.getStringExtra("MOVIE_POSTER");
            ArrayList<String> genres = intent.getStringArrayListExtra("MOVIE_GENRES");
            ArrayList<String> actors = intent.getStringArrayListExtra("MOVIE_ACTORS");
            String moviePlot = intent.getStringExtra("MOVIE_PLOT");
            double movieScore = intent.getDoubleExtra("MOVIE_SCORE", 0.0);
            int releaseYear = intent.getIntExtra("MOVIE_RELEASE_YEAR", 0);

            // Now you have all the data from the intent, you can use it as needed
            // For instance, set the values to corresponding TextViews or Views in your layout
            TextView txtMovieName = findViewById(R.id.txtmovieName);
            txtMovieName.setText(movieTitle);

            TextView txtPlot = findViewById(R.id.txtPlot);
            txtPlot.setText(moviePlot);

            TextView txtReleaseYear = findViewById(R.id.txtReleaseYear);
            txtReleaseYear.setText("Year:" + String.valueOf(releaseYear));

            TextView txtIMDB = findViewById(R.id.txtIMDB);
            txtIMDB.setText("IMDB Score:" + String.valueOf(movieScore));

            TextView txtGenre = findViewById(R.id.txtGenre);
            String genresString = TextUtils.join(", ", genres);
            txtGenre.setText("Genre: " + genresString);

            TextView txtActors = findViewById(R.id.txtActors);
            String actorsString = TextUtils.join(", ", actors);
            txtActors.setText("Actors:" + actorsString);

            TextView txtDirector = findViewById(R.id.txtDirector);
            String drc = "Director: " + movieDirector;
            txtDirector.setText(drc);

            ImageView imageView = findViewById(R.id.iw_movie_details);
            Picasso.get()
                    .load(moviePoster)
                    .placeholder(R.drawable.baseline_question_mark_24)
                    .into(imageView);
            // Set other retrieved data to respective views...
        }
    }
}