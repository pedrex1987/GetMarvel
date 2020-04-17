package br.com.pedrex.marvel.data.cloud

import br.com.pedrex.marvel.data.cloud.model.response.ObjectResponse
import br.com.pedrex.marvel.data.cloud.requests.Api
import br.com.pedrex.marvel.infra.BuildRetrofit
import rx.Observable

class CharacterCloud {

    private val api: Api

    init {
        val retrofit = BuildRetrofit().build()
        api = retrofit.create<Api>(Api::class.java)
    }

    fun getCharacters(page: Int) : Observable<ObjectResponse> {
        return api.fetchCharacters(
            page = page,
            limit = 30,
            order = "name"
        )
    }

    fun getCharactersByName(page: Int, name: String) : Observable<ObjectResponse> {
        return api.fetchCharactersByName(
            page = page,
            limit = 30,
            order = "name",
            name = name
        )
    }
}