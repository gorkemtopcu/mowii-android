package com.example.mowii_frontend.view.mainMenu.collection;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mowii_frontend.R;

public class CreateMovieCollectionDialogFragment extends DialogFragment {

    public interface CreateMovieCollectionDialogListener {
        void onCollectionCreate(String collectionName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_movie_collection_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editTextCollectionName = view.findViewById(R.id.etxt_collectionName);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        Button btnCreate = view.findViewById(R.id.btn_create);

        btnCancel.setOnClickListener(v -> dismiss());
        btnCreate.setOnClickListener(v -> {
            String collectionName = editTextCollectionName.getText().toString();
            if (!collectionName.isEmpty()) {
                CreateMovieCollectionDialogListener listener = (CreateMovieCollectionDialogListener) getActivity();
                if (listener != null) {
                    listener.onCollectionCreate(collectionName);
                }
                dismiss();
            } else {
                // Show an error or prompt the user to enter a valid collection name
                Toast.makeText(getContext(), "Please enter a collection name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
