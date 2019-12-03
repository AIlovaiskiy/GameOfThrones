package ru.skillbranch.gameofthrones.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.SearchTextTransmitter
import ru.skillbranch.gameofthrones.base.BaseViewModel

class CharactersListScreenViewModel(
    private val searchTextTransmitter: SearchTextTransmitter
) : BaseViewModel() {

    val pages: LiveData<List<String>> = MutableLiveData()

    val searchTextHandler: (text: String) -> Unit = { searchText ->
        searchTextTransmitter.accept(searchText)
    }

    val endSearch: () -> Unit = {
        searchTextTransmitter.accept("")
    }

    init {
        (pages as MutableLiveData).value = AppConfig.NEED_HOUSES.toList()
    }
}