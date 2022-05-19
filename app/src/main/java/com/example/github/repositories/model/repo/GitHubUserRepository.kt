package com.example.github.repositories.model.repo

import com.example.github.repositories.base.BaseRepository
import com.example.github.repositories.model.data.RepositoryDTO
import com.example.github.repositories.model.data.Response
import com.example.github.repositories.model.data.UserDTO
import com.example.github.repositories.model.api.ApiService
import com.example.github.repositories.model.util.ORDER
import com.example.github.repositories.model.util.QUERY
import com.example.github.repositories.model.util.SORT
import kotlinx.coroutines.launch



class GitHubUserRepository(private val apiService: ApiService) : BaseRepository() {

    fun getUserRepositories(
        url: String,
        onResponse: (MutableList<RepositoryDTO>) -> Unit,
        onError: ((String) -> Unit)? = null
    ) {
        scope.launch {
            apiService.getUserRepositories(url).let {
                if (it.isSuccessful) {
                    it.body()?.let { it1 ->
                        onResponse.invoke(it1)
                    }
                } else {
                    onError?.invoke(it.errorBody().toString())
                }
            }
        }
    }

    fun searchRepositories(
        onResponse: (Response) -> Unit,
        onError: ((String) -> Unit)? = null
    ) {
        scope.launch {
            apiService.searchRepositories(getQueryMap()).let {
                if (it.isSuccessful) {
                    it.body()?.let { it1 ->
                        onResponse.invoke(it1)
                    }
                } else {
                    onError?.invoke(it.errorBody().toString())
                }
            }
        }
    }

    fun getUser(
        userName: String,
        onResponse: (UserDTO) -> Unit,
        onError: ((String) -> Unit)? = null
    ) {
        scope.launch {
            apiService.getUser(userName).let {
                if (it.isSuccessful) {
                    it.body()?.let { it1 ->
                        onResponse.invoke(it1)
                    }
                } else {
                    onError?.invoke(it.errorBody().toString())
                }
            }
        }
    }

    private fun getQueryMap(): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["q"] = QUERY
        map["sort"] = SORT
        map["order"] = ORDER
        return map
    }
}