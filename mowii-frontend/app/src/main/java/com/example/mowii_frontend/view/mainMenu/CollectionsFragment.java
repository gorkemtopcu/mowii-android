package com.example.mowii_frontend.view.mainMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.view.recyclerView.RecyclerViewInterface;

public class CollectionsFragment extends Fragment implements RecyclerViewInterface {

    public CollectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collections, container, false);
    }

    @Override
    public void onItemClick(int position) {

    }
}
