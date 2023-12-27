package com.example.mowii_frontend.view.mainMenu;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.view.mainMenu.collection.CollectionsFragment;
import com.example.mowii_frontend.view.mainMenu.collection.CreateMovieCollectionDialogFragment;
import com.example.mowii_frontend.view.mainMenu.home.MoviesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class BottomNavigationMenu extends AppCompatActivity{

    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_menu);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Initialize the fragment map
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.movie_collection, new CollectionsFragment());
        fragmentMap.put(R.id.navigation_home, new MoviesFragment());
        fragmentMap.put(R.id.user_profile, new ProfileFragment());

        // Set the initial fragment
        loadFragment(fragmentMap.get(R.id.navigation_home));

        // Handle item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = fragmentMap.get(item.getItemId());
                return loadFragment(selectedFragment);
            }
        });

        // Programmatically select the "Home" item
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
            return true;
        }
        return false;
    }
}
