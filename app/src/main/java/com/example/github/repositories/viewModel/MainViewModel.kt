package com.example.github.repositories.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.github.repositories.base.BaseViewModel
import com.example.github.repositories.model.data.RepositoryDTO

class MainViewModel : BaseViewModel() {

    val repositories = MutableLiveData<List<RepositoryDTO>>()

    fun searchRepositories() {
        showLoading.set(true)
        repository?.searchRepositories({
            showLoading.set(false)
            repositories.postValue(it.items)
        }, {
            showLoading.set(false)
            repositories.postValue(ArrayList())
        })
    }

    override fun onCleared() {
        repository?.cancel()
        super.onCleared()
    }
}