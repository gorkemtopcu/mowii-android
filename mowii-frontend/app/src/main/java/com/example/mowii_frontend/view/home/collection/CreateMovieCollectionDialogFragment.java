package com.example.mowii_frontend.view.home.collection;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mowii_frontend.R;

public class CreateMovieCollectionDialogFragment extends DialogFragment {

    private Button btnCancel;
    private Button btnCreate;
    private ProgressBar pbCreateCollection;

    public interface CreateMovieCollectionDialogListener {
        void onCollectionCreate(String collectionName);
    }

    private CreateMovieCollectionDialogListener listener;

    public void setListener(CreateMovieCollectionDialogListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_movie_collection_dialog, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getDialog() != null && getDialog().getWindow() != null) {
            WindowManager.LayoutParams params;
            params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT; // Set your desired width here
            getDialog().getWindow().setAttributes(params);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }


        EditText editTextCollectionName = view.findViewById(R.id.etxt_collectionName);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnCreate = view.findViewById(R.id.btn_create);
        pbCreateCollection = view.findViewById(R.id.pb_createCollection);

        btnCancel.setOnClickListener(v -> dismiss());
        btnCreate.setOnClickListener(v -> {
            String collectionName = editTextCollectionName.getText().toString();
            if (!collectionName.isEmpty()) {
                if (listener != null) {
                    listener.onCollectionCreate(collectionName);
                }
            } else {
                // Show an error or prompt the user to enter a valid collection name
                Toast.makeText(getContext(), "Please enter a collection name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Button getBtnCreate() {
        return btnCreate;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public ProgressBar getPbCreateCollection() {
        return pbCreateCollection;
    }
}
