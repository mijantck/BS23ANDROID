package com.example.bs23android.ui.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bs23android.R;
import com.example.bs23android.data.model.RepositoryModel;
import com.example.bs23android.ui.activity.DetailActivity;
import com.google.gson.Gson;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {
    private List<RepositoryModel.Item> repositories;

    public RepositoryAdapter(List<RepositoryModel.Item> repositories) {
        this.repositories = repositories;

    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        RepositoryModel.Item repository = repositories.get(position);
        holder.bind(repository);

        holder.itemView.setOnClickListener(v -> {
            // Start DetailActivity with the selected repository

            Gson gson = new Gson();
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("repository", gson.toJson(repository));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public void setRepositories(List<RepositoryModel.Item> repositories){
        this.repositories.addAll(repositories);
        notifyDataSetChanged();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView descriptionTextView;
        private ImageView imageView;
        private TextView langTextView;
        private TextView starCoundTextView;
        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            imageView = itemView.findViewById(R.id.imageView);
            langTextView = itemView.findViewById(R.id.langId);
            starCoundTextView = itemView.findViewById(R.id.starCount);

        }

        public void bind(RepositoryModel.Item repository) {

            Glide.with(itemView.getContext())
                    .load(repository.getOwner().getAvatarUrl())
                    .centerCrop()
                    .into(imageView);

            nameTextView.setText(repository.getName());
            descriptionTextView.setText(repository.getDescription());
            langTextView.setText(repository.getLanguage());
            starCoundTextView.setText(repository.getStargazersCount()+"");
            // You can bind other data as needed
        }
    }
}

