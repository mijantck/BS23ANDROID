package com.example.bs23android.ui.viewmodel;
import com.example.bs23android.data.repository.RepositoryRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class RepositoryViewModelFactory implements ViewModelProvider.Factory {
    private RepositoryRepository repositoryRepository;

    public RepositoryViewModelFactory(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RepositoryViewModel.class)) {
            return (T) new RepositoryViewModel(repositoryRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

