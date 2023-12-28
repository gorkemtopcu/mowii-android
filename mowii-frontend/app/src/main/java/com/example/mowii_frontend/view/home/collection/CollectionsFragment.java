package com.example.mowii_frontend.view.home.collection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.databinding.FragmentCollectionsBinding;
import com.example.mowii_frontend.model.MovieCollection;
import com.example.mowii_frontend.viewModel.MovieCollectionViewModel;

import java.util.ArrayList;

public class CollectionsFragment extends Fragment {

    private FragmentCollectionsBinding binding;
    private final ArrayList<MovieCollection> data = new ArrayList<>();

    private MovieCollectionViewModel movieCollectionViewModel;

    public CollectionsFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collections, container, false);
        binding = FragmentCollectionsBinding.bind(view);
        binding.rvCollections.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieCollectionViewModel = new ViewModelProvider(requireActivity()).get(MovieCollectionViewModel.class);
        movieCollectionViewModel.getAllCollectionsResult().observe(getViewLifecycleOwner(), collectionResult -> {
            if (collectionResult != null && collectionResult.isSuccess()) {
                onRequestSuccessful(collectionResult.getData());
            } else {
                onRequestFailed();
            }
            binding.pbCollections.setVisibility(View.INVISIBLE);
        });
        movieCollectionViewModel.getAllCollections();

        return view;
    }


    private void onRequestFailed() {
        binding.txtAllcollections.setText(getString(R.string.error));
        binding.txtAllcollections.setVisibility(View.VISIBLE);
    }


    private void onRequestSuccessful(ArrayList<MovieCollection> results) {
        if (results != null && !results.isEmpty()) {
            onItemExists(results);
        } else {
            onNoItems();
        }
    }

    private void onNoItems() {
        binding.txtAllcollections.setText(getString(R.string.no_item));
        binding.txtAllcollections.setVisibility(View.VISIBLE);
    }

    private void onItemExists(ArrayList<MovieCollection> results) {
        data.clear();
        data.addAll(results);
        MovieCollectionAdapter adapter = new MovieCollectionAdapter(getActivity(), results, movieCollectionViewModel);
        binding.rvCollections.setAdapter(adapter);
        binding.rvCollections.setVisibility(View.VISIBLE);
    }
}