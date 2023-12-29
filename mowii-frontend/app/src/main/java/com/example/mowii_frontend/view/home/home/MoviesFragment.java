package com.example.mowii_frontend.view.home.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.databinding.FragmentHomeBinding;
import com.example.mowii_frontend.model.Movie;
import com.example.mowii_frontend.viewModel.MovieViewModel;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


public class MoviesFragment extends Fragment {

    private MovieViewModel movieViewModel;
    private FragmentHomeBinding binding;
    private final ArrayList<Movie> data = new ArrayList<>();

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.bind(view);
        binding.rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
        movieViewModel.getAllMoviesResult().observe(getViewLifecycleOwner(), movieResult -> {
            if (movieResult != null && movieResult.isSuccess()) {
                onRequestSuccessful(movieResult.getData());
            } else {
                onRequestFailed();
            }
            binding.pbMovies.setVisibility(View.INVISIBLE);
        });
        movieViewModel.getAllMovies();

        return binding.getRoot();
    }

    private void onRequestFailed() {
        binding.txtMovies.setText(getString(R.string.error));
        binding.txtMovies.setVisibility(View.VISIBLE);
    }

    private void onRequestSuccessful(ArrayList<Movie> results) {
        if (results != null && !results.isEmpty()) {
            onItemExists(results);
        } else {
            onNoItems();
        }
    }

    private void onNoItems() {
        binding.txtMovies.setText(getString(R.string.no_item));
        binding.txtMovies.setVisibility(View.VISIBLE);
        binding.rvMovies.setVisibility(View.GONE);
    }

    private void onItemExists(ArrayList<Movie> results) {
        data.clear();
        data.addAll(results);
        MovieAdapter adapter = new MovieAdapter(getActivity(), results);
        binding.rvMovies.setAdapter(adapter);
        binding.rvMovies.setVisibility(View.VISIBLE);
        binding.txtMovies.setVisibility(View.GONE);
    }
}
