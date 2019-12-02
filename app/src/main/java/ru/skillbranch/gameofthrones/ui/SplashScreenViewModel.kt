package ru.skillbranch.gameofthrones.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.base.BaseViewModel
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes
import ru.skillbranch.gameofthrones.data.remote.res.toShortName
import ru.skillbranch.gameofthrones.repositories.GameOfThroneRepository
import ru.skillbranch.gameofthrones.routers.SplashScreenRouter

class SplashScreenViewModel(
    private val router: SplashScreenRouter,
    private val repository: GameOfThroneRepository
) : BaseViewModel() {

    init {
        loadData()
    }

    private fun loadData() {
        launch {
            val data = repository.getNeedHouseWithCharacters(*AppConfig.NEED_HOUSES)
            val houses = mutableListOf<HouseRes>()
            val character = mutableListOf<CharacterRes>()
            data.forEach { (house, characters) ->
                houses.add(house)
                character.addAll(characters
                    .map { characterResponse ->
                        characterResponse.houseId = house.name.toShortName()
                        characterResponse
                    })
            }
            repository.insertHouses(houses)
            repository.insertCharacters(character)
            withContext(Dispatchers.Main) {
                router.goToCharactersList()
            }
        }
    }
}

