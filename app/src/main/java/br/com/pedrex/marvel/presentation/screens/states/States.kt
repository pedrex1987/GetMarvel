package br.com.pedrex.marvel.presentation.screens.states

import br.com.pedrex.marvel.presentation.model.Character

sealed class States {
    class LoadCharacters (val characters: List<Character>) : States()
    class Error(val message: String) : States()
    object Loading : States()
}