package com.example.mowii_frontend;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use a Handler to post a delayed action
        new Handler().postDelayed(() -> {
            // Create an Intent to navigate to the RegistrationActivity
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);

            // Start the RegistrationActivity
            startActivity(intent);

            // Finish the current activity (splash screen) to prevent going back
            finish();
        }, 2000);
    }
}