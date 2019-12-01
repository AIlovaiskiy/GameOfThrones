package ru.skillbranch.gameofthrones.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.skillbranch.gameofthrones.data.dao.CharacterDao
import ru.skillbranch.gameofthrones.data.dao.DatabaseDao
import ru.skillbranch.gameofthrones.data.dao.HouseDao
import ru.skillbranch.gameofthrones.data.local.entities.Character
import ru.skillbranch.gameofthrones.data.local.entities.House

@Database(entities = [Character::class, House::class], version = 1)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {

    abstract fun houseDao(): HouseDao
    abstract fun characterDao(): CharacterDao
    abstract fun databaseDao(): DatabaseDao
}


internal class Converter {
    @TypeConverter
    fun fromString(value: String): List<String> = value.split(",")

    @TypeConverter
    fun fromArrayList(list: List<String>) = list.joinToString(",")
}