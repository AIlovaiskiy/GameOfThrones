package ru.skillbranch.gameofthrones.storage

import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes

interface RemoteStorage {

    fun getAllHouses(): List<HouseRes> {
       return emptyList()
    }

    fun getNeedHouses(houseNames: Array<out String>): List<HouseRes> {
        return emptyList()
    }

    fun getNeedHouseWithCharacters(houseNames: Array<out String>): List<Pair<HouseRes, List<CharacterRes>>> {
        return emptyList()
    }
}