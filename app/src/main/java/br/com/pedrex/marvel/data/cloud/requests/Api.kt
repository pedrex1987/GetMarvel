package br.com.pedrex.marvel.data.cloud.requests

import br.com.pedrex.marvel.data.cloud.model.response.ObjectResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface Api {

    @GET("v1/public/characters")
    fun fetchCharacters(
        @Query("offset") page: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") order: String
    ): Observable<ObjectResponse>

    @GET("v1/public/characters")
    fun fetchCharactersByName(
        @Query("offset") page: Int,
        @Query("limit") limit: Int,
        @Query("orderBy") order: String,
        @Query("nameStartsWith") name: String
    ): Observable<ObjectResponse>
}