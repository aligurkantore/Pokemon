package com.codingurkan.pokeapplication.repository

import com.codingurkan.pokeapplication.api.ApiService
import com.codingurkan.pokeapplication.data.CharacterResponseModel
import com.codingurkan.pokeapplication.data.remote.responses.Pokemon
import com.codingurkan.pokeapplication.data.remote.responses.PokemonList
import com.codingurkan.pokeapplication.util.Resource
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(private var service: ApiService){

    suspend fun getPokemonList(limit : Int, offset : Int) : Resource<PokemonList>{
        val response = try {
            service.getPokemonList(limit,offset)
        } catch (e: Exception){
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(name: String) : Resource<Pokemon>{
        val response = try {
            service.getPokemonInfo(name)
        } catch (e: Exception){
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

    suspend fun charactersListRequest(pages : Int) : Resource<CharacterResponseModel>{
        val response = try {
            service.characterRequest(pages)
        }catch (e: Exception){
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }
}