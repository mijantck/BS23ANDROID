package com.example.bs23android.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bs23android.R;
import com.example.bs23android.data.model.RepositoryModel;

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

        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }

        public void bind(RepositoryModel.Item repository) {
            nameTextView.setText(repository.getName());
            descriptionTextView.setText(repository.getDescription());
            // You can bind other data as needed
        }
    }
}

