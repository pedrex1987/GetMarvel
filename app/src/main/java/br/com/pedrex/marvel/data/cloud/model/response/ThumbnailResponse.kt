package br.com.pedrex.marvel.data.cloud.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ThumbnailResponse (
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
) : Serializable