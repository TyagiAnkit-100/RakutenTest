package com.example.github.repositories.base

import androidx.lifecycle.ViewModel
import com.example.github.repositories.model.api.ApiService

open class BaseViewModel : ViewModel() {

    protected var api: ApiService? = null

    init {
        val nc = NetworkCommunicator.instance
        api = nc.createApiClient(ApiService::class.java)
    }

}

