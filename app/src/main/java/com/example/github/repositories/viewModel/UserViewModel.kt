package com.example.github.repositories.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.github.repositories.base.BaseViewModel
import com.example.github.repositories.model.data.RepositoryDTO
import com.example.github.repositories.model.data.UserDTO
import kotlinx.coroutines.launch

class UserViewModel : BaseViewModel() {

    val user = MutableLiveData<UserDTO>()
    val repositories = MutableLiveData<List<RepositoryDTO>>()

    fun fetchRepositories(reposUrl: String) {
        showLoading.set(true)
        viewModelScope.launch {
            repository?.getUserRepositories(reposUrl, {
                showLoading.set(false)
                repositories.postValue(it)
            }, {
                showLoading.set(false)
                repositories.postValue(ArrayList())
            })
        }
    }

    fun fetchUser(userName: String) {
        showLoading.set(true)
        viewModelScope.launch {
            repository?.getUser(userName, {
                showLoading.set(false)
                user.postValue(it)
            }, {
                showLoading.set(false)
                repositories.postValue(ArrayList())
            })
        }
    }
}