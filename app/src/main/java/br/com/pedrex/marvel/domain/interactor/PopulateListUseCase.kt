package br.com.pedrex.marvel.domain.interactor

import br.com.pedrex.marvel.data.CharacterRepository
import br.com.pedrex.marvel.presentation.model.Character
import rx.Observable

class PopulateListUseCase {

    private val repository = CharacterRepository()
    private var count = 1

    fun buildUseCaseObservable() : Observable<List<Character>> {
        return repository.getCharacters(count++)
    }
}