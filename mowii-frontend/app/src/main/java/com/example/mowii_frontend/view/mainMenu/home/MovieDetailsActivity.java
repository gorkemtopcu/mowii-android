package com.example.mowii_frontend.view.mainMenu.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mowii_frontend.R;

import java.util.ArrayList;

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
            int movieId = intent.getIntExtra("MOVIE_ID", -1);
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
            txtReleaseYear.setText(releaseYear);

            TextView txtIMDB = findViewById(R.id.txtIMDB);
            txtIMDB.setText((int) movieScore);

            TextView txtGenre = findViewById(R.id.txtGenre);
            txtGenre.setText((CharSequence) genres);

            TextView txtDirector = findViewById(R.id.txtDirector);
            txtDirector.setText(movieDirector);
            // Set other retrieved data to respective views...
        }
    }
}