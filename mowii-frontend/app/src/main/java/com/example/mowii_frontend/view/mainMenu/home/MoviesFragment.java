package com.example.mowii_frontend.view.mainMenu.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mowii_frontend.databinding.FragmentCollectionsBinding;
import com.example.mowii_frontend.model.Movie;
import com.example.mowii_frontend.view.recyclerView.RecyclerViewInterface;
import com.example.mowii_frontend.viewModel.HomeViewModel;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


public class MoviesFragment extends Fragment implements RecyclerViewInterface{

    private FragmentCollectionsBinding binding;
    private final ArrayList<Movie> movieModels = new ArrayList<>();

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCollectionsBinding.inflate(getLayoutInflater());
        binding.rvCollections.setLayoutManager(new LinearLayoutManager(getActivity()));

        HomeViewModel collectionViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        collectionViewModel.getAllMoviesResult().observe(getViewLifecycleOwner(), movieResult -> {
            if (movieResult != null && movieResult.isSuccess()) {
                onRequestSuccessful(movieResult.getData());
            } else {
                onRequestFailed();
            }
            binding.pbCollections.setVisibility(View.INVISIBLE);
        });
        collectionViewModel.getAllMovies();

        return binding.getRoot();
    }

    private void onRequestFailed() {
        binding.txtErrorMessage.setVisibility(View.VISIBLE);
    }

    private void onRequestSuccessful(ArrayList<Movie> results) {
        if (results != null && !results.isEmpty()) {
            onItemExists(results);
        } else {
            onNoItems();
        }
    }

    private void onNoItems() {
        binding.txtNoItemsMessage.setVisibility(View.VISIBLE);
    }

    private void onItemExists(ArrayList<Movie> results) {
        movieModels.clear();
        movieModels.addAll(results);
        MovieAdapter adapter = new MovieAdapter(getActivity(), results);
        binding.rvCollections.setAdapter(adapter);
        binding.rvCollections.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click if needed
    }
}
