package com.example.mowii_frontend.view.mainMenu.collection;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.model.Movie;
import com.example.mowii_frontend.model.MovieCollection;

import java.util.List;

public class MovieCollectionAdapter extends RecyclerView.Adapter<MovieCollectionAdapter.MovieCollectionViewHolder> {
    Context ctx;
    List<MovieCollection> data;

    public MovieCollectionAdapter(Context ctx, List<MovieCollection> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public MovieCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.rv_collection_row, parent, false);
        return new MovieCollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCollectionViewHolder holder, int position) {
        holder.collectionName.setText(data.get(position).getName());
        holder.userName.setText(data.get(position).getUserName());
        int likeIcon = false ? R.drawable.like_fill_icon : R.drawable.like_border_icon;
        holder.likes.setCompoundDrawablesWithIntrinsicBounds(0,0,likeIcon, 0);
        holder.likes.setText(data.get(position).getLike() + " ");

       // holder.row.setOnClickListener(v -> onRowClicked(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void onRowClicked(int position) {
        MovieCollection selectedCollection = data.get(position);

        Intent intent = new Intent(ctx, CollectionDetails.class);
        intent.putExtra("collectionId", selectedCollection.getId());
        intent.putExtra("collectionName", selectedCollection.getName());
        intent.putExtra("likeCount", selectedCollection.getLike());

        ctx.startActivity(intent);
    }

    public static class MovieCollectionViewHolder extends RecyclerView.ViewHolder {
        TextView collectionName;
        TextView userName;
        TextView likes;
        ConstraintLayout row;

        public MovieCollectionViewHolder(@NonNull View itemView) {
            super(itemView);
            collectionName = itemView.findViewById(R.id.txt_cname);
            userName = itemView.findViewById(R.id.txt_user);
            likes = itemView.findViewById(R.id.txt_likes);
            row = itemView.findViewById(R.id.rv_collection_row);
        }
    }
}
