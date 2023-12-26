package com.example.mowii_frontend.view.mainMenu.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mowii_frontend.R;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

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

        Intent intent = getIntent();
        if (intent != null) {
            String movieId = intent.getStringExtra("MOVIE_ID");
            String movieTitle = intent.getStringExtra("MOVIE_TITLE");
            String movieDirector = intent.getStringExtra("MOVIE_DIRECTOR");
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
            txtReleaseYear.setText(String.valueOf(releaseYear));

            TextView txtIMDB = findViewById(R.id.txtIMDB);
            txtIMDB.setText(String.valueOf(movieScore));

            TextView txtGenre = findViewById(R.id.txtGenre);
            String genresString = TextUtils.join(", ", genres);
            txtGenre.setText(genresString);

            TextView txtActors = findViewById(R.id.txtActors);
            String actorsString = TextUtils.join(", ", actors);
            txtActors.setText(actorsString);

            TextView txtDirector = findViewById(R.id.txtDirector);
            String drc = "Director: " + movieDirector;
            txtDirector.setText(drc);
            // Set other retrieved data to respective views...
        }
    }
}