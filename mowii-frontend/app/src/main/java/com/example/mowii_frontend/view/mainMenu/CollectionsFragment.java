package com.example.mowii_frontend.view.mainMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.model.Movie;
import com.example.mowii_frontend.model.User;
import com.example.mowii_frontend.view.recyclerView.RecyclerViewInterface;
import com.example.mowii_frontend.viewModel.CollectionsViewModel;
import com.example.mowii_frontend.viewModel.LogInViewModel;

import java.util.ArrayList;
import java.util.List;

import com.example.mowii_frontend.model.Collection;

public class CollectionsFragment extends Fragment implements RecyclerViewInterface {

    ArrayList<Collection> collectionModels = new ArrayList<>();
    private CollectionsViewModel collectionViewModel;

    public CollectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rv_collection_row, container, false);

        collectionViewModel = new ViewModelProvider(this).get(CollectionsViewModel.class);
        collectionViewModel.getAllCollectionsResult().observe(getViewLifecycleOwner(), collectionResult -> {
            if(collectionResult != null) {
                if(collectionResult.isSuccess()){
                    onRequestSuccessful(collectionResult.getData());
                } else {
                    onRequestFailed();
                }
            }
        });

        return view;
    }

    private void onRequestFailed() {
    }

    private void onRequestSuccessful(ArrayList<Collection> results) {
        if(results != null && results.isEmpty()) { onItemExists(results);}
        else { onNoItems(results);}
    }


    private void onNoItems(ArrayList<Collection> results) {

        
    }

    private void onItemExists(ArrayList<Collection> results) {
        collectionModels.addAll(results);

    }

    @Override
    public void onItemClick(int position) {

    }


}
