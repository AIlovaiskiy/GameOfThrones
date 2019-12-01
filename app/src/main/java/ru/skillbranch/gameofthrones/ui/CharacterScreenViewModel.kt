package ru.skillbranch.gameofthrones.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.skillbranch.gameofthrones.base.BaseViewModel
import ru.skillbranch.gameofthrones.data.local.entities.CharacterFull
import ru.skillbranch.gameofthrones.repositories.GameOfThroneRepository
import ru.skillbranch.gameofthrones.routers.CharacterScreenRouter

class CharacterScreenViewModel(
    private val router: CharacterScreenRouter,
    private val repository: GameOfThroneRepository,
    private val characterId: String
) : BaseViewModel() {


    val characterData: LiveData<CharacterFull> = MutableLiveData()

    init {
        loadData()
    }

    private fun loadData() {
        launch {
            val character = repository.findCharacterFullById(characterId)
            withContext(Dispatchers.Main) {
                (characterData as MutableLiveData).value = character
            }
        }
    }
    fun back() = router.back()
    val parentClick: (characterId: String) -> Unit = { id ->
        router.goToCharacterFull(id)
    }

}