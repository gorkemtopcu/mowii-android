package com.example.mowii_frontend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupFragment extends Fragment {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        // Switch to Login
        TextView switchText = view.findViewById(R.id.txt_switch);
        switchText.setOnClickListener(v -> {
            RegistrationActivity registrationActivity = (RegistrationActivity) requireActivity();
            registrationActivity.selectRegistrationFragment(new LoginFragment());
        });

        // Signup button clicked
        Button signupButton = view.findViewById(R.id.btn_signup);
        signupButton.setOnClickListener(this::onSignUpButtonClicked);

        usernameEditText = view.findViewById(R.id.etxt_username);
        emailEditText = view.findViewById(R.id.etxt_email);
        passwordEditText = view.findViewById(R.id.etxt_password);

        return view;
    }

    private void onSignUpButtonClicked(View view) {

        String enteredUsername = usernameEditText.getText().toString();
        String enteredEmail = emailEditText.getText().toString();
        String enteredPassword = passwordEditText.getText().toString();

        if (!isValidUsername(enteredUsername)){
            Toast.makeText(requireContext(), "Invalid username! Username must be at least 3 characters.", Toast.LENGTH_SHORT).show();
        }
        else if (!isValidEmail(enteredEmail)) {
            Toast.makeText(requireContext(), "Invalid email!", Toast.LENGTH_SHORT).show();
        }
        else if (!isValidPassword(enteredPassword)) {
            Toast.makeText(requireContext(), "Invalid password! Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
        }
        else {
            // TODO: try to register user to database
            Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidUsername(String username) {
        return !TextUtils.isEmpty(username) && username.length() >= 3;
    }


    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }
}