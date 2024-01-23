package com.codingurkan.pokeapplication.ui.fragments.pokelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingurkan.pokeapplication.R
import com.codingurkan.pokeapplication.data.remote.responses.Result
import com.codingurkan.pokeapplication.databinding.FragmentPokeListBinding
import com.codingurkan.pokeapplication.ui.adapter.PokemonListAdapter
import com.codingurkan.pokeapplication.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeListFragment : Fragment() {

    private var binding : FragmentPokeListBinding? = null
    private var adapter : PokemonListAdapter? = null
    private lateinit var mContext : Context
    private lateinit var mViewModel : PokeListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokeListBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        observeData()
        observeData2()
        mViewModel.getPokemonList(10,0)
    }

    private fun setViewModel(){
        mViewModel = ViewModelProvider(this)[PokeListViewModel::class.java]
    }


    private fun setAdapter(data : List<Result>){
        adapter = PokemonListAdapter(data,object : PokemonListAdapter.ItemCLickListener{
            override fun click(data: Result) {
                val bundle = Bundle()
                bundle.putSerializable("detail",data)
                findNavController().navigate(R.id.action_pokeListFragment_to_pokeDetailFragment)
            }
        })
        binding?.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(mContext)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

     private fun observeData() {
        mViewModel.pokemonLiveData.observe(viewLifecycleOwner){
            setAdapter(it.results)
        }
    }

    private fun observeData2(){
        mViewModel.pokemonListLiveData.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    //İstek yükleniyor
                }
                is Resource.Success ->{
                    val pokemonList = it.data
                }
                is Resource.Error ->{
                    val error = getString(R.string.app_name)
                }

                else -> {}
            }
        }
    }
}