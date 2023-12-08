package com.example.bs23android.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
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
import com.example.bs23android.utils.TimeUtils;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        private TextView pushUpdateId;
        public RepositoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            imageView = itemView.findViewById(R.id.imageView);
            langTextView = itemView.findViewById(R.id.langId);
            starCoundTextView = itemView.findViewById(R.id.starCount);
            pushUpdateId = itemView.findViewById(R.id.pushUpdateId);

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

//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                String lastUpdateTime = TimeUtils.formatUpdateTime(itemView.getContext(), repository.getPushedAt());
//                pushUpdateId.setText(lastUpdateTime);
//            }

            String lastUpdateTime = convertUtcToRelative(repository.getPushedAt(), itemView.getContext());
            pushUpdateId.setText(lastUpdateTime);

            // You can bind other data as needed
        }
    }

    private String convertUtcToRelative(String utcDate, Context context) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
            Date date = sdf.parse(utcDate);

            long timeInMillis = date.getTime();
            long now = System.currentTimeMillis();

            return DateUtils.getRelativeTimeSpanString(timeInMillis, now, DateUtils.MINUTE_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return utcDate; // Return original date string if parsing fails
        }
    }
}

