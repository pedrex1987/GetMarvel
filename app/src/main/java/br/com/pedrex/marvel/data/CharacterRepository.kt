package br.com.pedrex.marvel.data

import br.com.pedrex.marvel.data.cloud.CharacterCloud
import br.com.pedrex.marvel.data.mapper.CharacterToModelMapper
import br.com.pedrex.marvel.presentation.model.Character
import rx.Observable

class CharacterRepository {

    private val mapper = CharacterToModelMapper()
    private val cloud = CharacterCloud()

    fun getCharacters(page: Int): Observable<List<Character>> {
        return cloud.getCharacters(page).map { mapper.transform(it.data.characters) }
    }
}