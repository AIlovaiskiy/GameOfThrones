package ru.skillbranch.gameofthrones.data.remote.res

import ru.skillbranch.gameofthrones.data.local.entities.Character

data class CharacterRes(
    val url: String,
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val titles: List<String> = listOf(),
    val aliases: List<String> = listOf(),
    val father: String,
    val mother: String,
    val spouse: String,
    val allegiances: List<String> = listOf(),
    val books: List<String> = listOf(),
    val povBooks: List<Any> = listOf(),
    val tvSeries: List<String> = listOf(),
    val playedBy: List<String> = listOf()
) {
    var houseId: String = String()
}

fun CharacterRes.toCharacter(): Character = Character(
    id = url.replace("\\D".toRegex(), ""),
    name = name,
    gender = gender,
    culture = culture,
    born = born,
    died = died,
    titles = titles,
    aliases = aliases,
    father = father,
    mother = mother,
    spouse = spouse,
    houseId = houseId
)