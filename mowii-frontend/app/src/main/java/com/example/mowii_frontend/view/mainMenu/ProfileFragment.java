package com.example.mowii_frontend.view.mainMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.databinding.FragmentProfileBinding;
import com.example.mowii_frontend.manager.UserManager;
import com.example.mowii_frontend.model.User;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        binding = FragmentProfileBinding.bind(view);
        User user = UserManager.getInstance().getCurrentUser();

        if (user != null) {
            binding.userProfile.setVisibility(View.VISIBLE);
            binding.txtError.setVisibility(View.GONE);
            binding.txtTotalCollections.setText("Total Collections: " + user.getCollectionCount());
            binding.txtTotalLikes.setText("Total Likes: " + user.getTotalLikes());
            binding.txtUsername.setText(user.getName());
        }
        else {
            binding.userProfile.setVisibility(View.GONE);
            binding.txtError.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
