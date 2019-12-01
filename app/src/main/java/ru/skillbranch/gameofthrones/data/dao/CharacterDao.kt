package ru.skillbranch.gameofthrones.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.skillbranch.gameofthrones.data.local.entities.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg character: Character)

    @Query("DELETE FROM Character")
    fun deleteAll()

    @Query("SELECT * FROM Character")
    fun getAll(): List<Character>


    @Query("SELECT * FROM Character WHERE id = :id")
    fun getCharacterById(id: String): Character

    @Query("SELECT * FROM Character WHERE houseId = :id ORDER BY name")
    fun getCharacterByHouseId(id: String): List<Character>
}