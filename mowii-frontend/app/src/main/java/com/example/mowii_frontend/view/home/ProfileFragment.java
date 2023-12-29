package com.example.mowii_frontend.view.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.databinding.FragmentProfileBinding;
import com.example.mowii_frontend.manager.UserManager;
import com.example.mowii_frontend.model.MovieCollection;
import com.example.mowii_frontend.model.User;
import com.example.mowii_frontend.view.home.collection.CreateMovieCollectionDialogFragment;
import com.example.mowii_frontend.view.home.collection.MovieCollectionAdapter;
import com.example.mowii_frontend.viewModel.MovieCollectionViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements CreateMovieCollectionDialogFragment.CreateMovieCollectionDialogListener{
    User myUser = UserManager.getInstance().getCurrentUser();
    FragmentProfileBinding binding;
    private final ArrayList<MovieCollection> data = new ArrayList<>();
    private MovieCollectionViewModel movieCollectionViewModel;
    private CreateMovieCollectionDialogFragment createMovieCollectionDialogFragment;
    private int likeCount;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        binding = FragmentProfileBinding.bind(view);
        binding.rvMycollections.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.btnCreateCollection.setOnClickListener(v -> showCreateMovieCollectionDialog());

        movieCollectionViewModel = new ViewModelProvider(requireActivity()).get(MovieCollectionViewModel.class);

        if (myUser != null) {
            binding.userProfile.setVisibility(View.VISIBLE);
            binding.txtProfileError.setVisibility(View.GONE);

            binding.txtUsername.setText(myUser.getName());
            movieCollectionViewModel.getUserCollectionsResult().observe(getViewLifecycleOwner(), userCollectionsResult->{
                if (userCollectionsResult.isSuccess()){
                    onUserCollectionsFetchSuccessful(userCollectionsResult.getData());
                } else {
                    onUserCollectionsFetchFailed();
                }
            });
            movieCollectionViewModel.getUserCollections(myUser.getId());

            movieCollectionViewModel.likeCollectionResult().observe(getViewLifecycleOwner(), likeCollectionResult -> {
                if(likeCollectionResult.isSuccess()) {
                    binding.txtTotalLikes.setText("Total Likes: " + ++likeCount);
                }
            });

            movieCollectionViewModel.unlikeCollectionResult().observe(getViewLifecycleOwner(), unlikeCollectionResult -> {
                if(unlikeCollectionResult.isSuccess()) {
                    binding.txtTotalLikes.setText("Total Likes: " + --likeCount);
                }
            });
        } else {
            binding.userProfile.setVisibility(View.GONE);
            binding.txtProfileError.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void showCreateMovieCollectionDialog() {
        createMovieCollectionDialogFragment= new CreateMovieCollectionDialogFragment();
        createMovieCollectionDialogFragment.setListener(this); // Set the listener
        createMovieCollectionDialogFragment.show(getChildFragmentManager(), "NewCollectionDialog");
    }


    @SuppressLint("SetTextI18n")
    private void onUserCollectionsFetchSuccessful(ArrayList<MovieCollection> data) {
        setUserCollections(data);
        likeCount = myUser.getTotalLikes(data);
        binding.txtTotalCollections.setText("Total Collections: " + data.size());
        binding.txtTotalLikes.setText("Total Likes: " + likeCount);
    }

    @SuppressLint("SetTextI18n")
    private void onUserCollectionsFetchFailed() {
        binding.txtTotalCollections.setText("Total Collections: ?");
        binding.txtTotalLikes.setText("Total Likes: ?");
    }

    private void setUserCollections(List<MovieCollection> results) {
        if (results != null && !results.isEmpty()) {
            onItemExists(results);
        } else {
            onNoItems();
        }
        binding.pbMycollections.setVisibility(View.INVISIBLE);
    }

    private void onNoItems() {
        binding.txtMycollections.setText(getString(R.string.no_item));
        binding.txtMycollections.setVisibility(View.VISIBLE);
    }

    private void onItemExists(List<MovieCollection> results) {
        data.clear();
        data.addAll(results);
        MovieCollectionAdapter adapter = new MovieCollectionAdapter(getActivity(), data, movieCollectionViewModel,false);
        binding.rvMycollections.setAdapter(adapter);
        binding.rvMycollections.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCollectionCreate(String collectionName) {
        createMovieCollectionDialogFragment.getBtnCancel().setClickable(false);
        createMovieCollectionDialogFragment.getBtnCreate().setClickable(false);
        createMovieCollectionDialogFragment.getBtnCreate().setVisibility(View.GONE);
        createMovieCollectionDialogFragment.getPbCreateCollection().setVisibility(View.VISIBLE);

        movieCollectionViewModel.createCollectionResult().observe(getViewLifecycleOwner(), createCollectionResult ->{
            if (createCollectionResult.isSuccess()){
                createMovieCollectionDialogFragment.dismiss();
            } else {
                Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            }

            createMovieCollectionDialogFragment.getBtnCreate().setClickable(true);
            createMovieCollectionDialogFragment.getBtnCancel().setClickable(true);
            createMovieCollectionDialogFragment.getBtnCreate().setVisibility(View.VISIBLE);
            createMovieCollectionDialogFragment.getPbCreateCollection().setVisibility(View.GONE);
        });

        movieCollectionViewModel.createCollectionResult(myUser.getId(), collectionName);
    }
}
