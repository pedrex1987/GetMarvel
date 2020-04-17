package br.com.pedrex.marvel.data.mapper

import br.com.pedrex.marvel.data.cloud.model.response.CharacterResponse
import br.com.pedrex.marvel.presentation.model.Character

class CharacterToModelMapper {
    fun transform(response: CharacterResponse) : Character  {
        return Character(
            id = response.id,
            name = response.name,
            description = response.description,
            imageURI = getImageURI(response.thumbnail.path, response.thumbnail.extension),
            resourceURI = response.resourceURI
        )
    }

    fun transform (entity: List<CharacterResponse>) : List<Character> {
        return entity.map { transform(it) }
    }

    private fun getImageURI(path: String, extension: String): String {
        return path.plus(".").plus(extension)
    }
}