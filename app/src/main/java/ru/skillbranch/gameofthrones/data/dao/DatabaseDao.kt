package ru.skillbranch.gameofthrones.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DatabaseDao {

    @Transaction
    fun deleteAll() {
        deleteCharacter()
        deleteHouses()
    }

    @Transaction
    fun getRecordsCount(): Int {
        return getCharacterCount() + getHousesCount()
    }

    @Query("DELETE FROM House")
    fun deleteHouses()

    @Query("DELETE FROM Character")
    fun deleteCharacter()

    @Query("SELECT count(*) FROM House")
    fun getHousesCount(): Int

    @Query("SELECT count(*) FROM Character")
    fun getCharacterCount(): Int
}