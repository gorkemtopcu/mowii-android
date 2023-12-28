package com.example.mowii_frontend.view.home.collection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mowii_frontend.R;
import com.example.mowii_frontend.manager.UserManager;
import com.example.mowii_frontend.model.MovieCollection;
import com.example.mowii_frontend.model.User;
import com.example.mowii_frontend.viewModel.MovieCollectionViewModel;

import java.util.List;
import java.util.Objects;

public class MovieCollectionAdapter extends RecyclerView.Adapter<MovieCollectionAdapter.MovieCollectionViewHolder> {
    private final Context ctx;
    private final List<MovieCollection> data;
    private final MovieCollectionViewModel movieCollectionViewModel;
    private final User myUser = UserManager.getInstance().getCurrentUser();
    private final boolean isAddMovie;

    public interface MovieCollectionAdapterListener {
        void onMovieCollectionSelected(String collectionId);
    }

    private MovieCollectionAdapterListener listener;

    public void setListener(MovieCollectionAdapterListener listener) {
        this.listener = listener;
    }


    public MovieCollectionAdapter(Context ctx, List<MovieCollection> data, MovieCollectionViewModel movieCollectionViewModel, boolean isAddMovie) {
        this.ctx = ctx;
        this.data = data;
        this.movieCollectionViewModel = movieCollectionViewModel;
        this.isAddMovie = isAddMovie;
    }

    @NonNull
    @Override
    public MovieCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.rv_collection_row, parent, false);
        return new MovieCollectionViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieCollectionViewHolder holder, int position) {
        MovieCollection movieCollection = data.get(position);
        boolean isLiked = myUser.checkIsCollectionLiked(movieCollection.getId());
        holder.setLikeIcon(isLiked);
        holder.collectionName.setText(movieCollection.getName());
        holder.userName.setText(movieCollection.getUserName());
        holder.likes.setVisibility(isAddMovie ? View.INVISIBLE : View.VISIBLE);
        holder.setLikeText(movieCollection.getLike());
        holder.setRowClick(movieCollection, isAddMovie);
        holder.setLikeClicked(movieCollection);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MovieCollectionViewHolder extends RecyclerView.ViewHolder {
        TextView collectionName;
        TextView userName;
        TextView likes;
        ConstraintLayout row;

        public MovieCollectionViewHolder(@NonNull View itemView) {
            super(itemView);
            collectionName = itemView.findViewById(R.id.txtmovieName);
            userName = itemView.findViewById(R.id.txtReleaseYear);
            likes = itemView.findViewById(R.id.txt_likes);
            row = itemView.findViewById(R.id.rv_collection_row);
        }

        @SuppressLint("SetTextI18n")
        public void setLikeText(int likeCount) {
            likes.setText(likeCount + " ");

        }

        public void setLikeIcon(boolean isLiked) {
            int likeIcon = isLiked ? R.drawable.like_fill_icon : R.drawable.like_border_icon;
            likes.setCompoundDrawablesWithIntrinsicBounds(0, 0, likeIcon, 0);
        }

        public void setRowClick(MovieCollection selectedCollection, boolean isMovieAdd) {
            row.setOnClickListener(v -> {
                if (isMovieAdd) {
                    setItemClickMovieAdd(selectedCollection);
                } else {
                    setItemClick(selectedCollection);
                }
            });
        }

        private void setItemClick(MovieCollection selectedCollection){
            Intent intent = new Intent(ctx, CollectionDetails.class);
            intent.putExtra("collectionId", selectedCollection.getId());
            intent.putExtra("collectionName", selectedCollection.getName());
            intent.putExtra("creatorName", selectedCollection.getUserName());
            intent.putExtra("likeCount", selectedCollection.getLike());
            intent.putExtra("isBelongMyCollections", Objects.equals(myUser.getId(), selectedCollection.getUserId()));
            ctx.startActivity(intent);
        }

        private void setItemClickMovieAdd(MovieCollection selectedCollection) {
            if(listener != null) {
                listener.onMovieCollectionSelected(selectedCollection.getId());
            }
        }

        @SuppressLint("SetTextI18n")
        public void setLikeClicked(MovieCollection selectedCollection) {
            likes.setOnClickListener(v -> {
                // during backend operates do not take any input
                likes.setClickable(false);

                int likeCount = selectedCollection.getLike();
                String collectionId = selectedCollection.getId();
                boolean isLiked = myUser.checkIsCollectionLiked(collectionId);

                if (isLiked) {
                    // unlike collection
                    myUser.removeLikedCollection(collectionId);
                    selectedCollection.setLike(likeCount - 1);
                    setLikeText(likeCount - 1);
                    setLikeIcon(false);

                    movieCollectionViewModel.unlikeCollectionResult().observe((LifecycleOwner) ctx, unlikeCollectionResult -> {
                        if (unlikeCollectionResult == null || !unlikeCollectionResult.isSuccess()) {
                            onUnlikeFailed(selectedCollection);
                        }
                        likes.setClickable(true);
                    });
                    movieCollectionViewModel.unlikeCollection(selectedCollection.getId(), myUser.getId());
                } else {
                    // like collection
                    myUser.addLikedCollection(collectionId);
                    selectedCollection.setLike(likeCount + 1);
                    setLikeText(likeCount + 1);
                    setLikeIcon(true);

                    movieCollectionViewModel.likeCollectionResult().observe((LifecycleOwner) ctx, likeCollectionResult -> {
                        if (likeCollectionResult == null || !likeCollectionResult.isSuccess()) {
                            onLikeFailed(selectedCollection);
                        }
                        likes.setClickable(true);
                    });
                    movieCollectionViewModel.likeCollection(selectedCollection.getId(), myUser.getId());
                }
            });
        }

        private void onLikeFailed(MovieCollection movieCollection) {
            myUser.removeLikedCollection(movieCollection.getId());
            setLikeIcon(false);
            setLikeText(movieCollection.getLike());
        }

        private void onUnlikeFailed(MovieCollection movieCollection) {
            myUser.addLikedCollection(movieCollection.getId());
            setLikeIcon(true);
            setLikeText(movieCollection.getLike());
        }
    }
}
