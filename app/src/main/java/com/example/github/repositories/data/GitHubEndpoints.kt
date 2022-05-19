package com.example.github.repositories.data

import com.example.github.repositories.model.data.RepositoryDTO
import com.example.github.repositories.model.data.Response
import com.example.github.repositories.model.data.UserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubEndpoints {

    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String
    ): Call<UserDTO>

    @GET
    fun getUserRepositories(
        @Url userRepo: String
    ): Call<MutableList<RepositoryDTO>>
}