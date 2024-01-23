package com.codingurkan.pokeapplication.data.remote.responses

import java.io.Serializable

data class Result(
    val name: String,
    val url: String
) : Serializable