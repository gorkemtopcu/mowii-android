package com.example.mowii_frontend.view.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.view.auth.AuthenticationActivity;
import com.example.mowii_frontend.view.mainMenu.BottomNavigationMenu;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Simulating a delay for demonstration purposes
        new Handler().postDelayed(() -> {

            //Intent intent = new Intent(SplashActivity.this, AuthenticationActivity.class);
            Intent intent = new Intent(SplashActivity.this, BottomNavigationMenu.class);
            startActivity(intent);

            // Finish the current activity (splash screen) to prevent going back
            finish();
        }, 2000);
    }
}
