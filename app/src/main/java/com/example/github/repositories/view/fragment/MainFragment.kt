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

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

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
        Toast.makeText(requireContext(), "Network Disconnected!", Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel?.searchRepositories()
        viewDataBinding?.shimmerViewContainer?.startShimmerAnimation()
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
}