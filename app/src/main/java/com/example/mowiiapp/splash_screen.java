package com.example.mowiiapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DURATION = 1000; // Duration in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen); // Set your splash screen layout here

        // Handler to delay the start of the main activity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splash_screen.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the splash screen activity to prevent going back to it
        }, SPLASH_SCREEN_DURATION);
    }
}
