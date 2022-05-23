package com.example.github.repositories.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.github.repositories.BR
import com.example.github.repositories.R
import com.example.github.repositories.base.BaseFragment
import com.example.github.repositories.databinding.FragmentRetryBinding
import com.example.github.repositories.viewModel.MainViewModel

class RetryFragment(private val listener: RetryClickListener) :
    BaseFragment<FragmentRetryBinding, MainViewModel>() {

    override fun getLayout(): Int {
        return R.layout.fragment_retry
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
        viewDataBinding?.btnRetry?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            listener.onRetryClick()
        }
    }

    interface RetryClickListener {
        fun onRetryClick()
    }
}