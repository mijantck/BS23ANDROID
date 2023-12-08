package com.example.bs23android.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bs23android.data.model.RepositoryModel;
import com.example.bs23android.data.remote.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryRepositoryImpl implements RepositoryRepository {
    private ApiService apiService;

    public RepositoryRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public LiveData<List<RepositoryModel.Item>> searchRepositories(String query,String sort) {
        MutableLiveData<List<RepositoryModel.Item>> data = new MutableLiveData<>();

        apiService.searchRepositories(query,sort).enqueue(new Callback<RepositoryModel>() {
            @Override
            public void onResponse(Call<RepositoryModel> call, Response<RepositoryModel> response) {
                if (response.isSuccessful()) {
                    List<RepositoryModel.Item> items = response.body().getItems();
                    data.setValue(items);
                }
            }

            @Override
            public void onFailure(Call<RepositoryModel> call, Throwable t) {
                // Handle failure
            }
        });

        return data;
    }
}

