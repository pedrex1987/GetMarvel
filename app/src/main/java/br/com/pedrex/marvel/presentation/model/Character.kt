package br.com.pedrex.marvel.presentation.model

import java.io.Serializable

data class Character (
    val id: Int,
    val name: String,
    val description: String,
    val resourceURI: String,
    val image: String
) : Serializable