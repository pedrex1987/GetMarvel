package br.com.pedrex.marvel.data.cloud.model.response

import com.google.gson.annotations.SerializedName

data class DataResponse (
    @SerializedName("total") val id: Int,
    @SerializedName("results") val characters: List<CharacterResponse>
)
