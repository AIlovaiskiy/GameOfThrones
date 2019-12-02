package ru.skillbranch.gameofthrones.data.remote.res

import ru.skillbranch.gameofthrones.data.local.entities.House

data class HouseRes(
    val url: String,
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    val titles: List<String> = listOf(),
    val seats: List<String> = listOf(),
    val currentLord: String,
    val heir: String,
    val overlord: String,
    val founded: String,
    val founder: String,
    val diedOut: String,
    val ancestralWeapons: List<String> = listOf(),
    val cadetBranches: List<Any> = listOf(),
    val swornMembers: List<String> = listOf()
)

fun HouseRes.toHouse(): House = House(
    id = name.toShortName(),
    name = name,
    region = region,
    coatOfArms = coatOfArms,
    words = words,
    titles = titles,
    seats = seats,
    currentLord = currentLord,
    heir = heir,
    overlord = overlord,
    founded = founded,
    founder = founder,
    diedOut = diedOut,
    ancestralWeapons = ancestralWeapons
)

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