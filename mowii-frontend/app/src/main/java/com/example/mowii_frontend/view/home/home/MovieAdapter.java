package com.example.mowii_frontend.view.home.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.model.Movie;
import com.example.mowii_frontend.view.home.collection.AddMovieToCollectionsDialogFragment;
import com.example.mowii_frontend.view.home.collection.AddMovieToCollectionsDialogFragment.AddMovieToCollectionsDialogFragmentListener;
import com.example.mowii_frontend.viewModel.MovieCollectionViewModel;
import com.example.mowii_frontend.viewModel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements AddMovieToCollectionsDialogFragmentListener {

    private final Context ctx;
    private final ArrayList<Movie> items;
    private final boolean isBelongMyCollections;
    private final MovieViewModel movieViewModel;
    private final String movieCollectionId;
    private AddMovieToCollectionsDialogFragment addMovieToCollectionsDialogFragment;

    public MovieAdapter(Context ctx, ArrayList<Movie> items) {
        this.ctx = ctx;
        this.items = items;
        this.isBelongMyCollections = false;
        movieViewModel = null;
        movieCollectionId = null;
    }

    // Constructor when displaying AuthUser's collection movies
    public MovieAdapter(Context ctx, ArrayList<Movie> items, MovieViewModel movieViewModel, String movieCollectionId) {
        this.ctx = ctx;
        this.items = items;
        this.isBelongMyCollections = true;
        this.movieViewModel = movieViewModel;
        this.movieCollectionId = movieCollectionId;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(ctx).inflate(R.layout.rv_movie_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentMovie = items.get(position);
        holder.bind(currentMovie);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(ctx, MovieDetailsActivity.class);
            intent.putExtra("MOVIE_ID", currentMovie.getId()); // Pass the movie ID
            intent.putExtra("MOVIE_TITLE", currentMovie.getTitle()); // Pass the movie title
            intent.putExtra("MOVIE_DIRECTOR", currentMovie.getDirector()); // Pass the movie director
            intent.putStringArrayListExtra("MOVIE_GENRES", new ArrayList<>(currentMovie.getGenres()));
            intent.putStringArrayListExtra("MOVIE_ACTORS", new ArrayList<>(currentMovie.getActors()));
            intent.putExtra("MOVIE_PLOT", currentMovie.getPlot());
            intent.putExtra("MOVIE_SCORE", currentMovie.getImdbScore());
            intent.putExtra("MOVIE_RELEASE_YEAR", currentMovie.getReleaseYear());
            intent.putExtra("MOVIE_POSTER", currentMovie.getPosterLink());
            // Add more information as needed
            ctx.startActivity(intent);
        });

        if (isBelongMyCollections) {
            // activate remove button
            holder.getBtnAddMovie().setVisibility(View.GONE);
            holder.getBtnRemoveMovie().setVisibility(View.VISIBLE);
            holder.getBtnRemoveMovie().setOnClickListener(v->onBtnMovieRemoveClicked(currentMovie));
        } else {
            // activate add button
            holder.getBtnAddMovie().setVisibility(View.VISIBLE);
            holder.getBtnRemoveMovie().setVisibility(View.GONE);
            holder.getBtnRemoveMovie().setOnClickListener(v->onBtnMovieAddClicked(currentMovie));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void onBtnMovieAddClicked(Movie movie) {
        addMovieToCollectionsDialogFragment = new AddMovieToCollectionsDialogFragment(movie);
        addMovieToCollectionsDialogFragment.setListener(this);
        addMovieToCollectionsDialogFragment.show(addMovieToCollectionsDialogFragment.getParentFragmentManager(), "AddMovieToCollectionDialog");
    }

    @Override
    public void onMovieAdded(List<String> collectionIds, Movie selectedMovie, MovieCollectionViewModel movieCollectionViewModel) {
        movieCollectionViewModel.addMovieToCollections(collectionIds, selectedMovie.getId());
    }

    private void onBtnMovieRemoveClicked(Movie movie) {
        MovieCollectionViewModel movieCollectionViewModel = new MovieCollectionViewModel();
        movieCollectionViewModel.removeMovieFromCollectionResult().observe((LifecycleOwner) ctx, removeMovieCollectionResult ->{
            if (removeMovieCollectionResult.isSuccess()) {
                Objects.requireNonNull(movieViewModel).getMoviesByCollectionId(movieCollectionId);
            } else {
                Toast.makeText(ctx, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        movieCollectionViewModel.removeMovieFromCollection(movieCollectionId, movie.getId());
    }



    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView movieName;
        // private final TextView score;
        private final TextView movieYear;
        private final ImageButton btnAddMovie;
        private final ImageButton btnRemoveMovie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.txtmovieName);
            movieYear = itemView.findViewById(R.id.txtReleaseYear);
            btnAddMovie = itemView.findViewById(R.id.btn_addMovie);
            btnRemoveMovie = itemView.findViewById(R.id.btn_removeMovie);
        }

        public void bind(Movie movie) {
            movieName.setText(movie.getTitle());
            movieYear.setText(String.valueOf(movie.getReleaseYear()));
        }

        public TextView getMovieName(){
            return movieName;
        }

        public TextView getMovieYear(){
            return movieYear;
        }

        public ImageButton getBtnAddMovie(){
            return btnAddMovie;
        }

        public ImageButton getBtnRemoveMovie(){
            return btnRemoveMovie;
        }
    }
}
