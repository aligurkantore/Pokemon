package com.codingurkan.pokeapplication.data.remote.responses

import java.io.Serializable

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
) : Serializable