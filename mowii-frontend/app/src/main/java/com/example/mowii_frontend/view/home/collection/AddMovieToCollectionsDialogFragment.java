package com.example.mowii_frontend.view.home.collection;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.manager.UserManager;
import com.example.mowii_frontend.model.Movie;
import com.example.mowii_frontend.model.MovieCollection;
import com.example.mowii_frontend.model.User;
import com.example.mowii_frontend.viewModel.MovieCollectionViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AddMovieToCollectionsDialogFragment extends DialogFragment implements MovieCollectionAdapter.MovieCollectionAdapterListener {

    private final Movie selectedMovie;
    private HashSet<String> selectedCollections;
    private Button btnCancel;
    private Button btnAdd;
    private ProgressBar pbMyCollections;
    private ProgressBar pbAddMovie;
    private RecyclerView rvMyCollections;
    private TextView txtMyCollectionsError;
    MovieCollectionViewModel movieCollectionViewModel;

    public AddMovieToCollectionsDialogFragment(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    private final User myUser = UserManager.getInstance().getCurrentUser();

    @Override
    public void onCollectionClicked(String collectionId, boolean isSelected) {
        if (selectedCollections != null) {
            if (isSelected) {
                selectedCollections.add(collectionId);
            } else {
                selectedCollections.remove(collectionId);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_movie_to_collection, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieCollectionViewModel = new MovieCollectionViewModel();
        movieCollectionViewModel.getUserCollectionsResult().observe(getViewLifecycleOwner(), userCollectionsResult -> {
            if (userCollectionsResult.isSuccess()) {
                onUserCollectionsSuccessful(userCollectionsResult.getData());
            } else {
                onUserCollectionsFailed();
            }
            pbMyCollections.setVisibility(View.INVISIBLE);
        });
        movieCollectionViewModel.getUserCollections(myUser.getId());

        if (getDialog() != null && getDialog().getWindow() != null) {
            WindowManager.LayoutParams params;
            Window window = getDialog().getWindow();
            params = window.getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT; // Set your desired width here
            window.setAttributes(params);
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        btnCancel = view.findViewById(R.id.btn_cancel_add_movie);
        btnAdd = view.findViewById(R.id.btn_add_movie);
        pbMyCollections = view.findViewById(R.id.pb_addMovieMyCollections);
        pbAddMovie = view.findViewById(R.id.pb_add_movie);

        rvMyCollections = view.findViewById(R.id.rv_mycollections_movie_add);
        rvMyCollections.setLayoutManager(new LinearLayoutManager(getContext()));
        txtMyCollectionsError = view.findViewById(R.id.txt_mycollectionserror);

        btnCancel.setOnClickListener(v -> dismiss());
        btnAdd.setEnabled(false);
        btnAdd.setOnClickListener(v -> addMovieToCollections());
    }

    private void addMovieToCollections() {
        MovieCollectionViewModel movieCollectionViewModel = new MovieCollectionViewModel();
        List<String> collectionList = new ArrayList<>(selectedCollections);
        movieCollectionViewModel.addMovieToCollectionResult().observe(getViewLifecycleOwner(), addMovieToCollectionsResult -> {
            if (addMovieToCollectionsResult.isSuccess()) {
                dismiss();
            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            pbAddMovie.setVisibility(View.INVISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
            btnAdd.setClickable(true);
            btnCancel.setClickable(true);
        });

        btnCancel.setClickable(false);
        btnAdd.setClickable(false);
        btnAdd.setVisibility(View.INVISIBLE);
        pbAddMovie.setVisibility(View.VISIBLE);
        movieCollectionViewModel.addMovieToCollections(collectionList, selectedMovie.getId());
    }

    private void onUserCollectionsSuccessful(List<MovieCollection> results) {
        if (results != null && !results.isEmpty()) {
            onItemExists(results);
        } else {
            onNoItems();
        }
    }

    private void onUserCollectionsFailed() {
        txtMyCollectionsError.setVisibility(View.VISIBLE);
        rvMyCollections.setVisibility(View.INVISIBLE);
    }

    private void onItemExists(List<MovieCollection> items) {
        MovieCollectionAdapter adapter = new MovieCollectionAdapter(getActivity(), items, movieCollectionViewModel, true);
        adapter.setListener(this);
        rvMyCollections.setAdapter(adapter);
        rvMyCollections.setVisibility(View.VISIBLE);
        txtMyCollectionsError.setVisibility(View.INVISIBLE);
    }

    private void onNoItems() {
        txtMyCollectionsError.setText(R.string.no_item);
        txtMyCollectionsError.setVisibility(View.VISIBLE);
        rvMyCollections.setVisibility(View.INVISIBLE);
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public ProgressBar getPbMyCollections() {
        return pbMyCollections;
    }

    public RecyclerView getRvMyCollections() {
        return rvMyCollections;
    }
}

