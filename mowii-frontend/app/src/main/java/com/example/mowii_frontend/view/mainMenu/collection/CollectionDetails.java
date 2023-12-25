package com.example.mowii_frontend.view.mainMenu.collection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mowii_frontend.R;

public class CollectionDetails extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_details);

        // Retrieve the data from the intent
        String collectionName = getIntent().getStringExtra("collectionName");
        String userName = getIntent().getStringExtra("creatorName");
        String likeCount = String.valueOf(getIntent().getIntExtra("likeCount", 0));

        TextView twCollectionName = findViewById(R.id.txt_collection_name);
        twCollectionName.setText(collectionName);
        TextView twCollectionCreator = findViewById(R.id.txt_collection_creator);
        twCollectionCreator.setText("Collection Creator: " + userName);
        TextView twCollectionLikes = findViewById(R.id.txt_collection_likes);
        twCollectionLikes.setText("Likes: " + likeCount);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(""); // or use null
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    // Handle the back button click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();  // Go back to the previous activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
