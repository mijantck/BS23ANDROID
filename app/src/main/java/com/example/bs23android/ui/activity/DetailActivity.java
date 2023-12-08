package com.example.bs23android.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.bs23android.BR;
import com.example.bs23android.R;
import com.example.bs23android.data.model.RepositoryModel;
import com.example.bs23android.databinding.ActivityDetailBinding;
import com.example.bs23android.ui.viewmodel.DetailViewModel;
import com.google.gson.Gson;

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
    }
}
