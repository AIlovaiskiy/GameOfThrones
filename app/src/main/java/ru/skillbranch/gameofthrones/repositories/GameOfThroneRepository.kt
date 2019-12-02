package ru.skillbranch.gameofthrones.repositories

import kotlinx.coroutines.*
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
        val resultData: MutableList<HouseRes> = mutableListOf()
        var page = 0
        while (true) {
            val requestResult = remoteStorage.getAllHouses(++page)
            if (requestResult.isSuccessful) {
                val responseList = requestResult.body()
                if (responseList.isNullOrEmpty()) break
                resultData.addAll(responseList)
            }
        }
        return resultData
    }

    suspend fun getNeedHouses(vararg houseNames: String): List<HouseRes> {
        val job = SupervisorJob()
        val scope = CoroutineScope(Dispatchers.IO + job)
        val responses = mutableListOf<HouseRes>()
        scope.launch {
            val houses = houseNames
                .map { scope.async { loadHouse(it) } }
            responses.addAll(houses.awaitAll())
        }.join()
        return responses
    }

    suspend fun getNeedHouseWithCharacters(
        vararg houseNames: String
    ): List<Pair<HouseRes, List<CharacterRes>>> {
        val resultHouses: MutableList<Pair<HouseRes, List<CharacterRes>>> = mutableListOf()
        val houses = getNeedHouses(*houseNames)
        val job = SupervisorJob()
        val scope = CoroutineScope(Dispatchers.IO + job)
        scope.launch {
            val housesWithCharacters = houses
                .map { house ->
                    scope.async {
                        val members = house.swornMembers
                            .map { scope.async { loadCharacter(it) } }
                        Pair(house, members.awaitAll())
                    }
                }
            resultHouses.addAll(housesWithCharacters.awaitAll())
        }.join()
        return resultHouses
    }

    private suspend fun loadCharacter(memberUrl: String): CharacterRes {
        val requestCharacters = remoteStorage.getCharacter(memberUrl)
        if (requestCharacters.isSuccessful) {
            requestCharacters.body()?.let {
                return it
            }
        }
        throw RuntimeException("Все сломалось")
    }

    private suspend fun loadHouse(houseName: String): HouseRes {
        val requestHouse = remoteStorage.getNeedHouse(houseName)
        if (requestHouse.isSuccessful) {
            requestHouse.body()?.let {
                return it.first()
            }
        }
        throw RuntimeException("Все сломалось")
    }

    fun insertHouses(houses: List<HouseRes>) {
        localStorage.insertHouses(
            *houses
                .map { it.toHouse() }
                .toTypedArray()
        )
    }

    fun insertCharacters(characters: List<CharacterRes>) {
        localStorage.insertCharacters(
            *characters
                .map { it.toCharacter() }
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
            father = father?.toRelative(),
            mother = mother?.toRelative()
        )
    }

    fun isNeedUpdate(): Boolean =
        localStorage.isNeedUpdate()

}