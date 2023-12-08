package com.example.bs23android.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bs23android.data.model.RepositoryModel;

public class DetailViewModel extends ViewModel {

    private MutableLiveData<RepositoryModel.Item> selectedRepository = new MutableLiveData<>();

    public LiveData<RepositoryModel.Item> getSelectedRepository() {
        return selectedRepository;
    }

    public void setSelectedRepository(RepositoryModel.Item repository) {
        selectedRepository.setValue(repository);
    }

}
