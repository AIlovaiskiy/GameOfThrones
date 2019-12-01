package ru.skillbranch.gameofthrones.repositories

import ru.skillbranch.gameofthrones.data.local.entities.*
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes
import ru.skillbranch.gameofthrones.data.remote.res.toCharacter
import ru.skillbranch.gameofthrones.data.remote.res.toHouse
import ru.skillbranch.gameofthrones.data.storage.LocalStorage
import ru.skillbranch.gameofthrones.data.storage.RemoteStorage

class GameOfThroneRepository(
    private val remoteStorage: RemoteStorage,
    private val localStorage: LocalStorage
) {

    suspend fun getAllHouses(): List<HouseRes> {
        val resultData = remoteStorage.getAllHouses(1)
        return resultData.body() ?: emptyList()
    }

    suspend fun getNeedHouses(vararg houseNames: String): List<HouseRes> {
        return loadNeedHouse(*houseNames)
    }

    suspend fun getNeedHouseWithCharacters(
        vararg houseNames: String
    ): List<Pair<HouseRes, List<CharacterRes>>> {
        val resultHouses: MutableList<Pair<HouseRes, List<CharacterRes>>> = mutableListOf()
        val charactersOfHouse = mutableListOf<CharacterRes>()
        loadNeedHouse(*houseNames)
            .forEach { house ->
                charactersOfHouse.clear()
                house
                    .swornMembers
                    .forEach { memberUrl ->
                        val requestCharacters = remoteStorage.getCharacter(memberUrl)
                        if (requestCharacters.isSuccessful) {
                            requestCharacters.body()?.let { charactersOfHouse.add(it) }
                        }
                    }
                resultHouses.add(Pair(house, listOf(*charactersOfHouse.toTypedArray())))
            }
        return resultHouses
    }

    fun insertHouses(houses: List<HouseRes>) {
        localStorage.insertHouses(
            *houses
                .map { it.toHouse() }
                .toTypedArray()
        )
    }

    private suspend fun loadNeedHouse(vararg houseNames: String): List<HouseRes> {
        val resultData = mutableListOf<HouseRes>()
        houseNames.forEach { name ->
            val requestResult = remoteStorage.getNeedHouse(name)
            if (requestResult.isSuccessful) {
                requestResult.body()?.let { resultData.add(it.component1()) }
            }
        }
        return resultData
    }

    fun insertCharacters(characters: List<CharacterRes>) {
        localStorage.insertCharacters(
            *characters
                .map {
                    it.toCharacter()
                }
                .toTypedArray()
        )
    }

    fun dropDb() {
        localStorage.dropDb()
    }

    fun findCharactersByHouseName(name: String): List<CharacterItem> {
        val houseId = localStorage.findHouseIdByName(name)
        return localStorage
            .findCharactersByHouse(houseId)
            .map { it.toItem(name) }
    }

    fun findCharacterFullById(id: String): CharacterFull {

        val character = localStorage.findCharacterFullById(id)
        val father: Character? =
            localStorage.findCharacterFullById(character.father.replace("\\D".toRegex(), ""))
        val mother: Character? =
            localStorage.findCharacterFullById(character.mother.replace("\\D".toRegex(), ""))

        val house = localStorage.findHouseById(character.houseId)

        return character.toFull(
            houseName = house.name,
            houseWords = house.words,
            father = father?.toRelative(house.name),
            mother = mother?.toRelative(house.name)
        )
    }

    fun isNeedUpdate(): Boolean =
        localStorage.isNeedUpdate()

}