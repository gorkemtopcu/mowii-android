package com.example.mowii_frontend.view.mainMenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.model.Collection;
import com.example.mowii_frontend.view.recyclerView.Collection_RecyclerViewAdapter;
import com.example.mowii_frontend.view.recyclerView.RecyclerViewInterface;
import com.example.mowii_frontend.viewModel.CollectionsViewModel;

import java.util.ArrayList;

public class CollectionsFragment extends Fragment implements RecyclerViewInterface {

    private final ArrayList<Collection> collectionModels = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView errorTextView, noItemsTextView;
    private RecyclerView recyclerView;

    public CollectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collections, container, false);

        recyclerView = view.findViewById(R.id.rv_collections);
        progressBar = view.findViewById(R.id.pb_collections);
        errorTextView = view.findViewById(R.id.txt_error_message);
        noItemsTextView = view.findViewById(R.id.txt_no_items_message);

        CollectionsViewModel collectionViewModel = new ViewModelProvider(this).get(CollectionsViewModel.class);
        collectionViewModel.getAllCollectionsResult().observe(getViewLifecycleOwner(), collectionResult -> {
            if (collectionResult != null) {
                if (collectionResult.isSuccess()) {
                    onRequestSuccessful(collectionResult.getData());
                } else {
                    onRequestFailed();
                }
            }
        });
        collectionViewModel.getAllCollections();

        return view;
    }


    private void onRequestFailed() {
        progressBar.setVisibility(View.INVISIBLE);
        errorTextView.setVisibility(View.VISIBLE);
    }


    private void onRequestSuccessful(ArrayList<Collection> results) {
        if (results != null && !results.isEmpty()) {
            onItemExists(results);
        } else {
            onNoItems();
        }
    }

    private void onNoItems() {
        progressBar.setVisibility(View.INVISIBLE);
        noItemsTextView.setVisibility(View.VISIBLE);
    }

    private void onItemExists(ArrayList<Collection> results) {
        collectionModels.clear();
        collectionModels.addAll(results);
        Collection_RecyclerViewAdapter adapter = new Collection_RecyclerViewAdapter(requireContext(), collectionModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click if needed
    }
}
