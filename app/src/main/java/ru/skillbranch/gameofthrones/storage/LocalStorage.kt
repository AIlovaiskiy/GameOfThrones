package ru.skillbranch.gameofthrones.storage

import ru.skillbranch.gameofthrones.data.local.entities.CharacterFull
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes

object LocalStorage {

    fun insertHouses(houses: List<HouseRes>, complete: () -> Unit) {
        //TODO implement me
    }

    fun insertCharacters(Characters: List<CharacterRes>, complete: () -> Unit) {
        //TODO implement me
    }

    fun dropDb(complete: () -> Unit) {
        //TODO implement me
    }


    fun findCharactersByHouseName(name: String): List<CharacterItem> {
        return emptyList()
    }

    fun findCharacterFullById(id: String): CharacterFull {
        return CharacterFull(
            "sdf",
            "dfs",
            "sdfsd",
            "sdfsdf",
            "fsdfsd",
            listOf(),
            listOf(),
            "sdf",
            null,
            null
        )
    }

    fun isNeedUpdate(): Boolean {
        return false
    }
}