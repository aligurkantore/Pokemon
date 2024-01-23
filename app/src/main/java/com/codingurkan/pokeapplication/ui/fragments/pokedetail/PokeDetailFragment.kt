package com.codingurkan.pokeapplication.ui.fragments.pokedetail


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.codingurkan.pokeapplication.base.BaseFragment
import com.codingurkan.pokeapplication.databinding.FragmentPokeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeDetailFragment : BaseFragment<FragmentPokeDetailBinding,PokeDetailViewModel>() {
    override fun getBinding(
        inflater: LayoutInflater,
        group: ViewGroup?,
    ): FragmentPokeDetailBinding {
        return FragmentPokeDetailBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): PokeDetailViewModel {
        return ViewModelProvider(this)[PokeDetailViewModel::class.java]
    }

    override fun observeData() {
        TODO("Not yet implemented")
    }


}