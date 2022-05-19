package com.example.github.repositories.model.api

import com.example.github.repositories.model.data.RepositoryDTO
import com.example.github.repositories.model.data.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiService {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @QueryMap map: HashMap<String, String>,
    ): Response<com.example.github.repositories.model.data.Response>

    @GET("users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): Response<UserDTO>

    @GET
    suspend fun getUserRepositories(
        @Url userRepo: String
    ): Response<MutableList<RepositoryDTO>>
}