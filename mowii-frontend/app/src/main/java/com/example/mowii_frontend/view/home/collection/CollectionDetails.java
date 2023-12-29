package com.example.mowii_frontend.view.home.collection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.UserManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.databinding.ActivityCollectionDetailsBinding;
import com.example.mowii_frontend.model.Movie;
import com.example.mowii_frontend.view.home.home.MovieAdapter;
import com.example.mowii_frontend.viewModel.MovieCollectionViewModel;
import com.example.mowii_frontend.viewModel.MovieViewModel;

import java.util.ArrayList;

public class CollectionDetails extends AppCompatActivity {

    private final MovieCollectionViewModel movieCollectionViewModel = new MovieCollectionViewModel();
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
        boolean isBelongToMyCollections = getIntent().getBooleanExtra("isBelongMyCollections", false);

        TextView twCollectionName = findViewById(R.id.txt_collection_name);
        twCollectionName.setText(collectionName);
        TextView twCollectionCreator = findViewById(R.id.txt_collection_creator);
        twCollectionCreator.setText("Collection Creator: " + userName);
        TextView twCollectionLikes = findViewById(R.id.txt_collection_likes);
        twCollectionLikes.setText("Likes: " + likeCount);
        if (isBelongToMyCollections) {
            binding.btnDeleteCollection.setVisibility(View.VISIBLE);
            binding.btnDeleteCollection.setOnClickListener(v -> onBtnDeleteCollectionClicked());
        } else {
            binding.btnDeleteCollection.setVisibility(View.INVISIBLE);
        }
        ProgressBar pb = findViewById(R.id.pb_collection_details);
        pb.setVisibility(View.VISIBLE);

        //Get data from the view model based on collection id
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getMovieResultById().observe(this, movieResult -> {
            if (movieResult != null && movieResult.isSuccess()) {
                onGetMoviesRequestSuccessful(movieResult.getData());
            } else {
                onGetMoviesRequestFailed();
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

    private void onGetMoviesRequestFailed() {
        ProgressBar pb = findViewById(R.id.pb_collection_details);
        pb.setVisibility(View.INVISIBLE);
        TextView error = findViewById(R.id.txt_collectionMoviesError);
        error.setVisibility(View.VISIBLE);
    }

    private void onGetMoviesRequestSuccessful(ArrayList<Movie> results) {
        if (results != null && !results.isEmpty()) {
            onMovieExists(results);
        } else {
            onNoMovies();
        }
    }

    private void onNoMovies() {
        binding.txtCollectionMoviesNoItem.setVisibility(View.VISIBLE);
        binding.txtCollectionMoviesError.setVisibility(View.INVISIBLE);
        binding.rvMovies.setVisibility(View.INVISIBLE);
    }

    private void onMovieExists(ArrayList<Movie> results) {
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

    private void onBtnDeleteCollectionClicked() {
        movieCollectionViewModel.deleteCollection().observe(this, movieCollectionDeleteResult -> {
            if (movieCollectionDeleteResult.isSuccess()) {
                onDeleteCollectionSuccessful();
            } else {
                onDeleteCollectionFailed();
            }
        });
        movieCollectionViewModel.deleteCollection(collectionId);
    }

    private void onDeleteCollectionSuccessful() {
        finish();
    }

    private void onDeleteCollectionFailed() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
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
