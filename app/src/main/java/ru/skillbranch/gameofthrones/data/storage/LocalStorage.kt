package ru.skillbranch.gameofthrones.data.storage

import ru.skillbranch.gameofthrones.data.Database
import ru.skillbranch.gameofthrones.data.local.entities.Character
import ru.skillbranch.gameofthrones.data.local.entities.House

class LocalStorage(private val database: Database) {

    fun insertHouses(vararg houses: House) {
        database.houseDao().insertAll(*houses)
    }

    fun insertCharacters(vararg characters: Character) {
        database.characterDao().insertAll(*characters)
    }

    fun dropDb() {
        database.databaseDao().deleteAll()
    }

    fun findCharactersByHouse(houseId: String): List<Character> {
        return database.characterDao().getCharacterByHouseId(houseId)
    }

    fun findHouseIdByName(houseName: String): String {
        return database.houseDao().getHouseIdByName(houseName)
    }

    fun findCharacterFullById(id: String): Character {
        return database.characterDao().getCharacterById(id)
    }

    fun findHouseById(houseId: String): House {
        return database.houseDao().getHouseById(houseId)
    }

    fun isNeedUpdate(): Boolean {
        return database.databaseDao().getRecordsCount() == 0
    }
}