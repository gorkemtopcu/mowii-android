package com.example.mowii_frontend.view.mainMenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.databinding.FragmentProfileBinding;
import com.example.mowii_frontend.manager.UserManager;
import com.example.mowii_frontend.model.MovieCollection;
import com.example.mowii_frontend.model.User;
import com.example.mowii_frontend.view.mainMenu.collection.MovieCollectionAdapter;
import com.example.mowii_frontend.viewModel.MovieCollectionViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    private final ArrayList<MovieCollection> data = new ArrayList<>();
    private MovieCollectionViewModel movieCollectionViewModel;


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
        User user = UserManager.getInstance().getCurrentUser();
        movieCollectionViewModel = new ViewModelProvider(requireActivity()).get(MovieCollectionViewModel.class);

        if (user != null) {
            binding.userProfile.setVisibility(View.VISIBLE);
            binding.txtProfileError.setVisibility(View.GONE);
            binding.txtTotalCollections.setText("Total Collections: " + user.getCollectionCount());
            binding.txtTotalLikes.setText("Total Likes: " + user.getTotalLikes());
            binding.txtUsername.setText(user.getName());
            setUserCollections(user.getCollections());
            movieCollectionViewModel.likeCollectionResult().observe(getViewLifecycleOwner(), likeCollectionResult -> {
                if (likeCollectionResult.isSuccess())
                    binding.txtTotalLikes.setText("Total Likes: " + user.getTotalLikes());
            });
            movieCollectionViewModel.unlikeCollectionResult().observe(getViewLifecycleOwner(), unlikeCollectionResult -> {
                if (unlikeCollectionResult.isSuccess())
                    binding.txtTotalLikes.setText("Total Likes: " + user.getTotalLikes());
            });
        } else {
            binding.userProfile.setVisibility(View.GONE);
            binding.txtProfileError.setVisibility(View.VISIBLE);
        }

        return view;
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
        MovieCollectionAdapter adapter = new MovieCollectionAdapter(getActivity(), results, movieCollectionViewModel);
        binding.rvMycollections.setAdapter(adapter);
        binding.rvMycollections.setVisibility(View.VISIBLE);
    }
}
