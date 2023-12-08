package com.example.bs23android.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.bs23android.BR;
import com.example.bs23android.R;
import com.example.bs23android.data.model.RepositoryModel;
import com.example.bs23android.databinding.ActivityDetailBinding;
import com.example.bs23android.ui.viewmodel.DetailViewModel;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private DetailViewModel detailViewModel;
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        // Get the selected repository from the intent or ViewModel
        //RepositoryModel.Item selectedRepository = getIntent().getParcelableExtra("repository");
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("repository");
        RepositoryModel.Item selectedRepository = gson.fromJson(strObj, RepositoryModel.Item.class);

        Log.d("hjskdhfds", "onCreate: "+selectedRepository.getName());
        // Create DetailViewModel
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        // Set the selected repository to the ViewModel
        detailViewModel.setSelectedRepository(selectedRepository);

        Log.d("dfhskjh", "onCreate: "+selectedRepository.getName());
        // Bind ViewModel to the layout
        binding.setVariable(BR.viewModel, detailViewModel);
        binding.setLifecycleOwner(this);
        String lastUpdateTime = convertUtcToRelative(selectedRepository.getPushedAt(),DetailActivity.this);
        binding.updatedateAndTime.setText(lastUpdateTime);
        Glide.with(DetailActivity.this)
                .load(selectedRepository.getOwner().getAvatarUrl())
                .centerCrop()
                .into(binding.imageId);



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
