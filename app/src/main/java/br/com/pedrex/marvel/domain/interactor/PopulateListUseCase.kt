package br.com.pedrex.marvel.domain.interactor

import br.com.pedrex.marvel.presentation.model.Character
import rx.Observable

class PopulateListUseCase {

    fun buildUseCaseObservable() : Observable<List<Character>> {

        val hero = Character(
            id = 1,
            resourceURI = "out",
            description = "Primeiro heroi",
            name = "Goku",
            imageURI = "https://pbs.twimg.com/profile_images/943532664174366721/gD01TT7H_400x400.jpg"
        )

        val villain = Character(
            id = 2,
            resourceURI = "out",
            description = "O Vilão",
            name = "Tao PaiPai",
            imageURI = "https://2.bp.blogspot.com/_vwUqDynsvNY/SHKn4bWMvaI/AAAAAAAAAII/2p2_y7eZY5I/s400/Tao+Pai+Pai.jpg"
        )

        val list = arrayListOf<Character>(hero, villain)

        return Observable.just(list)
    }
}