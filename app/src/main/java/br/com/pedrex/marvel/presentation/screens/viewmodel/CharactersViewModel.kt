package br.com.pedrex.marvel.presentation.screens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pedrex.marvel.domain.interactor.PopulateListUseCase
import br.com.pedrex.marvel.presentation.model.Character
import br.com.pedrex.marvel.presentation.screens.states.States
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

interface InputCartViewModel {
    fun selectCharacter(character: Character)
    fun populateList()
}

interface OutputCartViewModel {
    val selectCarLiveData: LiveData<Character>
    val populateCarsLiveData: MutableLiveData<States>
}

interface ContractCartViewModel {
    val input: InputCartViewModel
    val output: OutputCartViewModel
}

class CharactersViewModel(
    val mainList: ArrayList<Character>
) : ContractCartViewModel,
    InputCartViewModel,
    OutputCartViewModel {

    private val useCase = PopulateListUseCase()

    override val input: InputCartViewModel get() = this
    override val output: OutputCartViewModel get() = this

    private val firstObservable = MutableLiveData<Character>()
    override val selectCarLiveData: LiveData<Character> get() = firstObservable

    private val populateObservable = MutableLiveData<States>()
    override val populateCarsLiveData: MutableLiveData<States> get() = populateObservable

    override fun selectCharacter(car: Character) {
        firstObservable.postValue(car)
    }

    override fun populateList() {
        useCase.buildUseCaseObservable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(40, TimeUnit.SECONDS)
            .subscribe(
                {
                    populateObservable.postValue(States.LoadCharacters(filterList(it)))
                },
                {
                    populateObservable.postValue(
                        States.Error(
                            it.message ?: "Erro não Identificado"
                        )
                    )
                    it.printStackTrace()
                }
            )
    }

    private fun filterList(newList: List<Character>): List<Character> {
        return if (mainList.isEmpty()) {
            newList
        } else {
            newList.filter {newPerson ->
                mainList.none { it.id == newPerson.id }
            }
        }
    }
}