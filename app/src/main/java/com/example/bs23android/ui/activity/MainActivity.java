package com.example.bs23android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.bs23android.R;
import com.example.bs23android.data.remote.ApiClient;
import com.example.bs23android.data.repository.RepositoryRepositoryImpl;
import com.example.bs23android.databinding.ActivityMainBinding;
import com.example.bs23android.ui.adapter.RepositoryAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    // Repository Adapter
    private RepositoryAdapter adapter;

    private RepositoryRepositoryImpl repository; // Add this line
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);


        binding.repositoryList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RepositoryAdapter(new ArrayList<>());
        binding.repositoryList.setAdapter(adapter);
        repository = new RepositoryRepositoryImpl(ApiClient.getApiService());
        fetchData();
    }


    private void fetchData() {
        // Call your repository method to get data and update the adapter
        repository.searchRepositories("android","stars").observe(this, repositories -> {
            if (repositories != null) {

                Log.d("djshfjk", "fetchData: "+repositories.size());
                adapter.setRepositories(repositories);
            }
        });
    }

}