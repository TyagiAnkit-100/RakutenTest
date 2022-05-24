package com.example.github.repositories.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.github.repositories.base.BaseViewModel
import com.example.github.repositories.model.data.RepositoryDTO
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    val repositories = MutableLiveData<List<RepositoryDTO>>()

    fun searchRepositories() {
        showLoading.set(true)
        viewModelScope.launch {
            repository?.searchRepositories({
                showLoading.set(false)
                repositories.postValue(it.items)
            }, {
                showLoading.set(false)
                repositories.postValue(ArrayList())
            })
        }
    }
}