package com.example.mowii_frontend.view.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.manager.UserManager;
import com.example.mowii_frontend.model.User;
import com.example.mowii_frontend.view.mainMenu.BottomNavigationMenu;
import com.example.mowii_frontend.viewModel.LogInViewModel;

public class LoginFragment extends Fragment {
    private Button loginButton;
    private TextView switchText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private LogInViewModel logInViewModel;
    private ProgressBar loginProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        switchText = view.findViewById(R.id.txt_switch);
        switchText.setOnClickListener(v -> {
            AuthenticationActivity authenticationActivity = (AuthenticationActivity) requireActivity();
            authenticationActivity.selectRegistrationFragment(new SignupFragment());
        });


        logInViewModel = new ViewModelProvider(this).get(LogInViewModel.class);
        logInViewModel.getAuthenticationResult().observe(getViewLifecycleOwner(), authResult -> {
            if (authResult != null) {
                if (authResult.isSuccess())  { onLoginSuccessful(authResult.getData()); }
                else { onLoginFailed(authResult.getErrorMessage()); }
            }
        });

        loginButton = view.findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this::onLoginButtonClicked);

        emailEditText = view.findViewById(R.id.etxt_email);
        passwordEditText = view.findViewById(R.id.etxt_password);
        loginProgressBar = view.findViewById(R.id.pb_login);

        return view;
    }

    private void onLoginButtonClicked(View view) {
        String enteredEmail = emailEditText.getText().toString();
        String enteredPassword = passwordEditText.getText().toString();
        loginButton.setVisibility(View.INVISIBLE);
        loginProgressBar.setVisibility(View.VISIBLE);
        switchText.setClickable(false);

        User user = new User(enteredEmail, enteredPassword);
        logInViewModel.authenticateUser(user);
    }

    private void onLoginSuccessful(User authenticatedUser){
        UserManager userManager = UserManager.getInstance();
        userManager.setCurrentUser(authenticatedUser);

        Intent intent = new Intent(requireActivity(), BottomNavigationMenu.class);
        startActivity(intent);

        // Finish the current activity (splash screen) to prevent going back
        requireActivity().finish();
    }

    private void onLoginFailed(String message) {
        loginButton.setVisibility(View.VISIBLE);
        loginProgressBar.setVisibility(View.INVISIBLE);
        switchText.setClickable(true);
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}