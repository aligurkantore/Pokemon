package com.codingurkan.pokeapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    private lateinit var binding : VB
    private lateinit var mViewModel : VM

    abstract fun getBinding(inflater: LayoutInflater,group: ViewGroup?) : VB

    abstract fun getViewModel() : VM

    abstract fun observeData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(inflater,container)
        mViewModel = getViewModel()
        return binding.root
    }
}