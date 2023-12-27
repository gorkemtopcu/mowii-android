package com.example.mowii_frontend.view.home.collection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.databinding.ActivityCollectionDetailsBinding;
import com.example.mowii_frontend.model.Movie;
import com.example.mowii_frontend.view.home.home.MovieAdapter;
import com.example.mowii_frontend.viewModel.MovieViewModel;

import java.util.ArrayList;

public class CollectionDetails extends AppCompatActivity {

    private MovieViewModel movieViewModel;
    private final ArrayList<Movie> data = new ArrayList<>();
    private ActivityCollectionDetailsBinding binding; // Add this line
    private String collectionId;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_details);

        // Initialize binding
        binding = ActivityCollectionDetailsBinding.inflate(getLayoutInflater());
        binding.rvMovies.setLayoutManager(new LinearLayoutManager(this));

        View view = binding.getRoot();
        setContentView(view);

        // Retrieve the data from the intent
        collectionId = getIntent().getStringExtra("collectionId");
        String collectionName = getIntent().getStringExtra("collectionName");
        String userName = getIntent().getStringExtra("creatorName");
        String likeCount = String.valueOf(getIntent().getIntExtra("likeCount", 0));

        TextView twCollectionName = findViewById(R.id.txt_collection_name);
        twCollectionName.setText(collectionName);
        TextView twCollectionCreator = findViewById(R.id.txt_collection_creator);
        twCollectionCreator.setText("Collection Creator: " + userName);
        TextView twCollectionLikes = findViewById(R.id.txt_collection_likes);
        twCollectionLikes.setText("Likes: " + likeCount);

        ProgressBar pb = findViewById(R.id.pb_collection_details);
        pb.setVisibility(View.VISIBLE);

        //Get data from the view model based on collection id
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getMovieResultById().observe((LifecycleOwner) this, movieResult -> {
            if (movieResult != null && movieResult.isSuccess()) {
                onRequestSuccessful(movieResult.getData());
            } else {
                onRequestFailed();
            }
            binding.pbCollectionDetails.setVisibility(View.GONE);
        });
        movieViewModel.getMoviesByCollectionId(collectionId);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(""); // or use null
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void onRequestFailed() {
        ProgressBar pb = findViewById(R.id.pb_collection_details);
        pb.setVisibility(View.INVISIBLE);
        TextView error = findViewById(R.id.txt_collectionMoviesError);
        error.setVisibility(View.VISIBLE);
    }

    private void onRequestSuccessful(ArrayList<Movie> results) {
        if (results != null && !results.isEmpty()) {
            onItemExists(results);
        } else {
            onNoItems();
        }
    }

    private void onNoItems() {
        binding.txtCollectionMoviesNoItem.setVisibility(View.VISIBLE);

        binding.txtCollectionMoviesError.setVisibility(View.INVISIBLE);
        binding.rvMovies.setVisibility(View.INVISIBLE);
    }

    private void onItemExists(ArrayList<Movie> results) {
        data.clear();
        data.addAll(results);
        boolean isBelongToUser = getIntent().getBooleanExtra("isBelongMyCollections", false);
        MovieAdapter adapter = isBelongToUser ?
                new MovieAdapter(this, results, movieViewModel, collectionId)
                : new MovieAdapter(this, results);
        binding.rvMovies.setAdapter(adapter);
        binding.txtCollectionMoviesNoItem.setVisibility(View.INVISIBLE);
        binding.txtCollectionMoviesError.setVisibility(View.INVISIBLE);

        binding.rvMovies.setVisibility(View.VISIBLE);
    }

    // Handle the back button click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();  // Go back to the previous activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
