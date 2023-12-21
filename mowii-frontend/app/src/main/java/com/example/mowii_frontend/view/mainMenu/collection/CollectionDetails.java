package com.example.mowii_frontend.view.mainMenu.collection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        Toolbar toolbar = findViewById(R.id.toolbar_collection_details);
        setSupportActionBar(toolbar);
        toolbar.setTitle(collectionName);
    }
}