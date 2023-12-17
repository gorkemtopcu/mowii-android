package com.example.mowii_frontend.view.mainMenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private ArrayList<Collection> collectionModels = new ArrayList<>();
    private CollectionsViewModel collectionViewModel;
    private Collection_RecyclerViewAdapter adapter;

    public CollectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collections, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_collections);

        Log.d("CollectionsFragment", "onCreateView: Initializing ViewModel");
        collectionViewModel = new ViewModelProvider(this).get(CollectionsViewModel.class);
        collectionViewModel.getAllCollectionsResult().observe(getViewLifecycleOwner(), collectionResult -> {
            Log.d("CollectionsFragment", "LiveData Observer triggered");
            if (collectionResult != null) {
                if (collectionResult.isSuccess()) {
                    Log.d("CollectionsFragment", "onCreateView: onRequestSuccessful called");
                    onRequestSuccessful(collectionResult.getData());
                } else {
                    Log.d("CollectionsFragment", "onCreateView: onRequestFailed called");
                    onRequestFailed();
                }
            }
        });

        Log.d("CollectionsFragment", "onCreateView: Initializing RecyclerView and Adapter");
        adapter = new Collection_RecyclerViewAdapter(requireContext(), collectionModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        Log.d("CollectionsFragment", "onCreateView: Returning view");
        return view;
    }


    private void onRequestFailed() {
        // Handle failure if needed
        View view = getView();
        if (view != null) {
            TextView errorTextView = view.findViewById(R.id.tv_error_message);
            errorTextView.setVisibility(View.VISIBLE);
            // You can set a specific error message
            errorTextView.setText("Failed to retrieve collections. Please try again later.");
        }
    }


    private void onRequestSuccessful(ArrayList<Collection> results) {
        if (results != null && !results.isEmpty()) {
            onItemExists(results);
        } else {
            onNoItems();
        }
    }

    private void onNoItems() {
        View view = getView();
        if (view != null) {
            TextView noItemsTextView = view.findViewById(R.id.tv_no_items_message);
            noItemsTextView.setVisibility(View.VISIBLE); // Make the TextView visible
        }
    }

    private void onItemExists(ArrayList<Collection> results) {
        collectionModels.clear();
        collectionModels.addAll(results);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click if needed
    }
}
