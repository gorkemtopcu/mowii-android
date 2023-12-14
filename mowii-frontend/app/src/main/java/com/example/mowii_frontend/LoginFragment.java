package com.example.mowii_frontend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class LoginFragment extends Fragment {
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView switchText = view.findViewById(R.id.txt_switch);
        switchText.setOnClickListener(v -> {
            RegistrationActivity registrationActivity = (RegistrationActivity) requireActivity();
            registrationActivity.selectRegistrationFragment(new SignupFragment());
        });

        emailEditText = view.findViewById(R.id.etxt_email);
        passwordEditText = view.findViewById(R.id.etxt_password);

        return view;
    }

    private void onLoginButtonClicked(View view) {
        String enteredEmail = emailEditText.getText().toString();
        String enteredPassword = passwordEditText.getText().toString();

        
    }
}