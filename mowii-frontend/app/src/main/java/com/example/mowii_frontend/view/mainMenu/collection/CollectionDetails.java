package com.example.mowii_frontend.view.mainMenu.collection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mowii_frontend.R;

public class CollectionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_details);

        // Retrieve the data from the intent
        String collectionName = getIntent().getStringExtra("collectionName");
        String userName = getIntent().getStringExtra("userName");
        int likeCount = getIntent().getIntExtra("likeCount", 0);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(collectionName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}