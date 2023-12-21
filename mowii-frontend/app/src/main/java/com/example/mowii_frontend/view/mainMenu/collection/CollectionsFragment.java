package com.example.mowii_frontend.view.mainMenu.collection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mowii_frontend.databinding.FragmentCollectionsBinding;
import com.example.mowii_frontend.model.MovieCollection;
import com.example.mowii_frontend.view.recyclerView.RecyclerViewInterface;
import com.example.mowii_frontend.viewModel.CollectionsViewModel;

import java.util.ArrayList;

public class CollectionsFragment extends Fragment implements RecyclerViewInterface {

    private FragmentCollectionsBinding binding;
    private final ArrayList<MovieCollection> movieCollectionModels = new ArrayList<>();

    public CollectionsFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCollectionsBinding.inflate(getLayoutInflater());
        binding.rvCollections.setLayoutManager(new LinearLayoutManager(getActivity()));

        CollectionsViewModel collectionViewModel = new ViewModelProvider(requireActivity()).get(CollectionsViewModel.class);
        collectionViewModel.getAllCollectionsResult().observe(getViewLifecycleOwner(), collectionResult -> {
            if (collectionResult != null && collectionResult.isSuccess()) {
                onRequestSuccessful(collectionResult.getData());
            } else {
                onRequestFailed();
            }
            binding.pbCollections.setVisibility(View.INVISIBLE);
        });
        collectionViewModel.getAllCollections();

        return binding.getRoot();
    }


    private void onRequestFailed() {
        binding.txtErrorMessage.setVisibility(View.VISIBLE);
    }


    private void onRequestSuccessful(ArrayList<MovieCollection> results) {
        if (results != null && !results.isEmpty()) {
            onItemExists(results);
        } else {
            onNoItems();
        }
    }

    private void onNoItems() {
        binding.txtNoItemsMessage.setVisibility(View.VISIBLE);
    }

    private void onItemExists(ArrayList<MovieCollection> results) {
        movieCollectionModels.clear();
        movieCollectionModels.addAll(results);
        MovieCollectionAdapter adapter = new MovieCollectionAdapter(getActivity(), results);
        binding.rvCollections.setAdapter(adapter);
        binding.rvCollections.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click if needed
    }
}