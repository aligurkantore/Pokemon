package com.codingurkan.pokeapplication.ui.fragments.pokelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codingurkan.pokeapplication.base.BaseViewModel
import com.codingurkan.pokeapplication.data.CharacterResponseModel
import com.codingurkan.pokeapplication.data.remote.responses.Pokemon
import com.codingurkan.pokeapplication.data.remote.responses.PokemonList
import com.codingurkan.pokeapplication.data.remote.responses.Sprites
import com.codingurkan.pokeapplication.repository.PokemonRepository
import com.codingurkan.pokeapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(private var repo : PokemonRepository) : BaseViewModel() {

    private var job : Job? = null
    val pokemonLiveData = MutableLiveData<PokemonList>()

    private val _pokemonListLiveData = MutableLiveData<Resource<PokemonList>>()
    val pokemonListLiveData: LiveData<Resource<PokemonList>> get() = _pokemonListLiveData


    fun getPokemonList(limit: Int, offset: Int) {
        viewModelScope.launch {
            _pokemonListLiveData.value = Resource.Loading()
            try {
                val result = repo.getPokemonList(limit, offset)
                _pokemonListLiveData.postValue(result)
                Log.d("agt", "getPokemonList: $result")
            } catch (e: Exception) {
                _pokemonListLiveData.value = e.message?.let { Resource.Error(it) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}