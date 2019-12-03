package ru.skillbranch.gameofthrones.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.skillbranch.gameofthrones.SearchTextTransmitter
import ru.skillbranch.gameofthrones.base.BaseViewModel
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.repositories.GameOfThroneRepository
import ru.skillbranch.gameofthrones.routers.CharacterListRouter

class CharacterListViewModel(
    private val router: CharacterListRouter,
    private val repository: GameOfThroneRepository,
    private val searchTextTransmitter: SearchTextTransmitter,
    private val houseName: String
) : BaseViewModel() {

    val characters: LiveData<List<CharacterItem>> = MutableLiveData()

    init {
        loadData()
        compositeDisposable.add(
            searchTextTransmitter
                .listenValues
                .map(::search)
                .subscribe()
        )
    }

    private fun search(searchText: String) {
        if (searchText.isEmpty()) {
            loadData()
            return
        }
        launch {
            val searchResult: List<CharacterItem> = characters
                .value
                ?.let { items ->
                    items.filter { characterItem ->
                        characterItem.name.startsWith(searchText, true)
                    }
                } ?: emptyList()
            withContext(Dispatchers.Main) {
                (characters as MutableLiveData).value = searchResult
            }
        }
    }


    private fun loadData() {
        launch {
            val charactersData = repository.findCharactersByHouseName(houseName)
            charactersData
                .map { item ->
                    item.clickListener = { router.goToCharacterFull(item.id) }
                }
            withContext(Dispatchers.Main) {
                (characters as MutableLiveData).value = charactersData
            }
        }
    }

}