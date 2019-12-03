package ru.skillbranch.gameofthrones.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.skillbranch.gameofthrones.R

@Entity(tableName = "House")
data class House(
    @PrimaryKey
    val id: String,
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    val titles: List<String>,
    val seats: List<String>,
    val currentLord: String, //rel
    val heir: String, //rel
    val overlord: String,
    val founded: String,
    val founder: String, //rel
    val diedOut: String,
    val ancestralWeapons: List<String>
)

fun String.toHouseLogo(): Int = when (this) {
    "House Stark of Winterfell" -> R.drawable.stark_coast_of_arms
    "House Lannister of Casterly Rock" -> R.drawable.lannister__coast_of_arms
    "House Targaryen of King's Landing" -> R.drawable.targaryen_coast_of_arms
    "House Greyjoy of Pyke" -> R.drawable.greyjoy_coast_of_arms
    "House Tyrell of Highgarden" -> R.drawable.targaryen_coast_of_arms
    "House Baratheon of Dragonstone" -> R.drawable.baratheon
    "House Nymeros Martell of Sunspear" -> R.drawable.martel_coast_of_arms
    else -> R.drawable.stark_coast_of_arms
}

fun String.toHousesColor(): Int = when (this) {
    "House Stark of Winterfell" -> R.color.stark_primary
    "House Lannister of Casterly Rock" -> R.color.lannister_primary
    "House Targaryen of King's Landing" -> R.color.targaryen_primary
    "House Greyjoy of Pyke" -> R.color.greyjoy_primary
    "House Tyrell of Highgarden" -> R.color.tyrel_primary
    "House Baratheon of Dragonstone" -> R.color.baratheon_primary
    "House Nymeros Martell of Sunspear" -> R.color.martel_primary
    else -> R.color.stark_primary
}

fun String.toShortName(): String = when (this) {
    "House Stark of Winterfell" -> "Stark"
    "House Lannister of Casterly Rock" -> "Lannister"
    "House Targaryen of King's Landing" -> "Targaryen"
    "House Greyjoy of Pyke" -> "Greyjoy"
    "House Tyrell of Highgarden" -> "Tyrell"
    "House Baratheon of Dragonstone" -> "Baratheon"
    "House Nymeros Martell of Sunspear" -> "Martell"
    else -> "Stark"
}