package com.codingurkan.pokeapplication.api

import com.codingurkan.pokeapplication.data.CharacterResponseModel
import com.codingurkan.pokeapplication.data.remote.responses.Pokemon
import com.codingurkan.pokeapplication.data.remote.responses.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit : Int,
        @Query("offset") offset : Int
    ) : PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name : String
    ) : Pokemon

    @GET("character/")
    suspend fun characterRequest(
        @Query("page") pages : Int
    ) : CharacterResponseModel

}