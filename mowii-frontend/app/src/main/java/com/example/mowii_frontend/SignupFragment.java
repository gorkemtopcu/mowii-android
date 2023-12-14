package com.example.mowii_frontend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SignupFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        TextView switchText = view.findViewById(R.id.txt_switch);
        switchText.setOnClickListener(v -> {
            RegistrationActivity registrationActivity = (RegistrationActivity) requireActivity();
            registrationActivity.selectRegistrationFragment(new LoginFragment());
        });
        // Add any specific initialization for LoginFragment
        return view;
    }
}