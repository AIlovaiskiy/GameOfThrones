package ru.skillbranch.gameofthrones.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.ValueTransmitter
import ru.skillbranch.gameofthrones.base.BaseViewModel
import ru.skillbranch.gameofthrones.repositories.GameOfThroneRepository
import ru.skillbranch.gameofthrones.routers.CharactersListScreenRouter

class CharactersListScreenViewModel(
    private val router: CharactersListScreenRouter,
    private val repository: GameOfThroneRepository,
    private val valueTransmitter: ValueTransmitter
) : BaseViewModel() {

    val pages: LiveData<List<String>> = MutableLiveData()


    val searchTextHandler: (text: String) -> Unit = { searchText ->
        valueTransmitter.accept(searchText)
    }

    val endSearch: () -> Unit = {
        valueTransmitter.accept("")
    }

    init {
        loadData()
    }

    private fun loadData() {
        (pages as MutableLiveData).value = AppConfig.NEED_HOUSES.toList()
    }
}