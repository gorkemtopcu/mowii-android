package com.example.mowii_frontend.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mowii_frontend.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Simulating a delay for demonstration purposes
        new Handler().postDelayed(() -> {
            // Check if the user is authenticated (replace with your authentication logic)
            //boolean isAuthenticated = checkAuthenticationStatus();

           // if (isAuthenticated) {
                // If authenticated, navigate to the MainActivity with the bottom navigation
                Intent intent = new Intent(MainActivity.this, BottomNavigationMenu.class);
                startActivity(intent);
           // } else {
                // If not authenticated, navigate to the login/signup flow
            //    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            //    startActivity(intent);
           // }

            // Finish the current activity (splash screen) to prevent going back
            finish();
        }, 2000);
    }

    // Replace this with authentication logic
    private boolean checkAuthenticationStatus() {

        return false;
    }
}
