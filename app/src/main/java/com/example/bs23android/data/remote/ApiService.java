package com.example.bs23android.data.remote;

import com.example.bs23android.data.model.RepositoryModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/repositories")
    Call<RepositoryModel> searchRepositories(
            @Query("q") String query,
            @Query("sort") String sort
    );
}
