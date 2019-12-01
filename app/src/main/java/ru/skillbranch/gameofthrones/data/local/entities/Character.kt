package ru.skillbranch.gameofthrones.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Character")
data class Character(
    @PrimaryKey
    val id: String,
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val titles: List<String> = listOf(),
    val aliases: List<String> = listOf(),
    val father: String, //rel
    val mother: String, //rel
    val spouse: String,
    val houseId: String//rel
)

data class CharacterItem(
    val id: String,
    val house: String, //rel
    val name: String,
    val titles: List<String>,
    val aliases: List<String>,
    var clickListener: (characterId: String) -> Unit = {}
)

data class CharacterFull(
    val id: String,
    val name: String,
    val words: String,
    val born: String,
    val died: String,
    val titles: List<String>,
    val aliases: List<String>,
    val house: String, //rel
    val father: RelativeCharacter?,
    val mother: RelativeCharacter?
)

data class RelativeCharacter(
    val id: String,
    val name: String,
    val house: String //rel
)

fun Character.toItem(houseName: String): CharacterItem = CharacterItem(
    id = id,
    house = houseName,
    name = name,
    titles = titles,
    aliases = aliases
)

fun Character.toFull(
    houseName: String,
    houseWords: String,
    father: RelativeCharacter?,
    mother: RelativeCharacter?
): CharacterFull = CharacterFull(
    id = id,
    name = name,
    words = houseWords,
    born = born,
    died = died,
    titles = titles,
    aliases = aliases,
    house = houseName,
    father = father,
    mother = mother
)

fun Character.toRelative(houseName: String): RelativeCharacter = RelativeCharacter(
    id = id,
    house = houseName,
    name = name
)