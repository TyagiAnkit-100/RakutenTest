package com.example.github.repositories.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.github.repositories.base.BaseViewModel
import com.example.github.repositories.data.GitHubEndpoints
import com.example.github.repositories.model.data.RepositoryDTO
import com.example.github.repositories.model.repo.GitHubUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : BaseViewModel() {

    var showLoading: ObservableField<Boolean> = ObservableField(false)
    val repositories = MutableLiveData<List<RepositoryDTO>>()
    private val repository by lazy {
        api?.let { GitHubUserRepository(it) }
    }

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

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service: GitHubEndpoints = retrofit.create(GitHubEndpoints::class.java)


    fun refresh() {
        GlobalScope.launch(Dispatchers.Main) {
           /* delay(1_000) // This is to simulate network latency, please don't remove!
            var response: Response?
            withContext(Dispatchers.IO) {
                response = service.searchRepositories(QUERY, SORT, ORDER).execute().body()
            }
            repositories.value = response?.items*/
        }
    }
}