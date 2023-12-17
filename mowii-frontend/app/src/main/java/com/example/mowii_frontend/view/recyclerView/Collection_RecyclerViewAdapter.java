package com.example.mowii_frontend.view.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.model.Collection;

import java.util.ArrayList;

public class Collection_RecyclerViewAdapter extends RecyclerView.Adapter<Collection_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<Collection> collectionsArrayList;

    public Collection_RecyclerViewAdapter(Context context, ArrayList<Collection> collectionsArrayList) {
        this.context = context;
        this.collectionsArrayList = collectionsArrayList;
    }

    @NonNull
    @Override
    public Collection_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_collection_row, parent, false);
        return new Collection_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Collection_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.userName.setText(collectionsArrayList.get(position).getUser().getName());
        holder.collectionName.setText(collectionsArrayList.get(position).getName());
        holder.likes.setText(collectionsArrayList.get(position).getLike());
    }

    @Override
    public int getItemCount() {
        return collectionsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
         TextView collectionName, userName, likes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            collectionName = itemView.findViewById(R.id.txt_cname);
            userName = itemView.findViewById(R.id.txt_user);
            likes = itemView.findViewById(R.id.txt_likes);
        }
    }
}
