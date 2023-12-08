package com.example.bs23android.data.repository;

import androidx.lifecycle.LiveData;

import com.example.bs23android.data.model.RepositoryModel;

import java.util.List;

public interface RepositoryRepository {

    LiveData<List<RepositoryModel.Item>> searchRepositories(String query,String sort);
}
