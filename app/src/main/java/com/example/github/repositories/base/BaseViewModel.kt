package com.example.github.repositories.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.github.repositories.model.api.ApiService
import com.example.github.repositories.model.repo.GitHubUserRepository

open class BaseViewModel : ViewModel() {
    protected var showLoading: ObservableField<Boolean> = ObservableField(false)
    protected var api: ApiService? = null

    init {
        val nc = NetworkCommunicator.instance
        api = nc.createApiClient(ApiService::class.java)
    }

    protected val repository by lazy {
        api?.let { GitHubUserRepository(it) }
    }
}

