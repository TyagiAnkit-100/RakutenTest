package com.example.github.repositories.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.github.repositories.BR
import com.example.github.repositories.R
import com.example.github.repositories.base.BaseFragment
import com.example.github.repositories.databinding.FragmentMainBinding
import com.example.github.repositories.model.repo.RepositoryAdapter
import com.example.github.repositories.viewModel.MainViewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(),
    RetryFragment.RetryClickListener {

    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onInternetUnavailable() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, RetryFragment(this))
            .addToBackStack("retry")
            .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchRepositories()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        mViewModel?.repositories?.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val adapter = RepositoryAdapter(it.take(20).toMutableList(), requireActivity())
                viewDataBinding?.rvGitRepo?.adapter = adapter
                viewDataBinding?.shimmerViewContainer?.stopShimmerAnimation()
            }
        }
    }

    private fun fetchRepositories() {
        mViewModel?.searchRepositories()
        viewDataBinding?.shimmerViewContainer?.startShimmerAnimation()
    }

    override fun onRetryClick() {
        fetchRepositories()
    }
}