package com.example.bs23android.ui.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bs23android.data.model.RepositoryModel;
import com.example.bs23android.data.repository.RepositoryRepository;

import java.util.List;

public class RepositoryViewModel extends ViewModel {
    private RepositoryRepository repositoryRepository;

    // Constructor for dependency injection
    public RepositoryViewModel(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    public LiveData<List<RepositoryModel.Item>> searchRepositories(String query,String sort) {
        return repositoryRepository.searchRepositories(query,sort);
    }
}

