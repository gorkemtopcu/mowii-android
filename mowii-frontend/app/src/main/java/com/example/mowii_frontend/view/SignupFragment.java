package com.example.mowii_frontend.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.mowii_frontend.viewModel.LogInViewModel;
import com.example.mowii_frontend.viewModel.SignUpViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.model.User;

public class SignupFragment extends Fragment {

    private Button signupButton;
    private TextView switchText;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private SignUpViewModel signUpViewModel;
    private ProgressBar signupProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        switchText = view.findViewById(R.id.txt_switch);
        switchText.setOnClickListener(v -> {
            RegistrationActivity registrationActivity = (RegistrationActivity) requireActivity();
            registrationActivity.selectRegistrationFragment(new LoginFragment());
        });

        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        signUpViewModel.getAuthenticationResult().observe(getViewLifecycleOwner(), registrationResult -> {
            if (registrationResult != null) {
                if (registrationResult.isSuccess())  { onRegistrationSuccessful();}
                else { onRegistrationFailed( registrationResult.getErrorMessage()); }
            }
        });

        // Signup button clicked
        signupButton = view.findViewById(R.id.btn_signup);
        signupButton.setOnClickListener(this::onSignUpButtonClicked);

        usernameEditText = view.findViewById(R.id.etxt_username);
        emailEditText = view.findViewById(R.id.etxt_email);
        passwordEditText = view.findViewById(R.id.etxt_password);
        signupProgressBar = view.findViewById(R.id.pb_signup);

        return view;
    }

    private void onSignUpButtonClicked(View view) {
        String enteredUsername = usernameEditText.getText().toString();
        String enteredEmail = emailEditText.getText().toString();
        String enteredPassword = passwordEditText.getText().toString();


        if (!isValidUsername(enteredUsername)){
            Toast.makeText(requireContext(), "Username must be at least 3 characters.", Toast.LENGTH_SHORT).show();
        }
        else if (!isValidEmail(enteredEmail)) {
            Toast.makeText(requireContext(), "Invalid email!", Toast.LENGTH_SHORT).show();
        }
        else if (!isValidPassword(enteredPassword)) {
            Toast.makeText(requireContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
        }
        else {
            signupButton.setVisibility(View.INVISIBLE);
            signupProgressBar.setVisibility(View.VISIBLE);
            switchText.setClickable(false);

            User user = new User(enteredUsername, enteredEmail, enteredPassword);
            signUpViewModel.registerUser(user);
        }
    }

    private void onRegistrationSuccessful() {
        Intent intent = new Intent(requireActivity(), BottomNavigationMenu.class);
        startActivity(intent);

        // Finish the current activity (splash screen) to prevent going back
        requireActivity().finish();
    }

    private void onRegistrationFailed(String message) {
        signupButton.setVisibility(View.VISIBLE);
        signupProgressBar.setVisibility(View.INVISIBLE);
        switchText.setClickable(true);
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
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