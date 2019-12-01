package ru.skillbranch.gameofthrones.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.skillbranch.gameofthrones.data.local.entities.House

@Dao
interface HouseDao {
    @Insert
    fun insertAll(vararg houses: House)

    @Query("DELETE FROM House")
    fun deleteAll()

    @Query("SELECT id FROM House WHERE name = :name")
    fun getHouseIdByName(name: String): String

    @Query("SELECT name FROM House WHERE id = :id")
    fun getHouseNameById(id: String): String

    @Query("SELECT * FROM House WHERE id = :houseId")
    fun getHouseById(houseId: String): House

    @Query("SELECT * FROM House")
    fun getAll(): List<House>
}