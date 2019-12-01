package ru.skillbranch.gameofthrones.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.ui.CharacterListFragment

class HousesPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle

) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private var housesName: List<String> = listOf()
    override fun getItemCount(): Int = housesName.size

    override fun createFragment(position: Int): Fragment =
        CharacterListFragment.getInstance(housesName[position])

    fun submitItem(items: List<String>) {
        housesName = items
        notifyDataSetChanged()
    }

    fun getTitle(position: Int): String = housesName[position].toShortName()

    fun getColor(position: Int): Int = housesName[position].toColors()

    private fun String.toShortName(): String = when (this) {
        "House Stark of Winterfell" -> "Stark"
        "House Lannister of Casterly Rock" -> "Lannister"
        "House Targaryen of King's Landing" -> "Targaryen"
        "House Greyjoy of Pyke" -> "Greyjoy"
        "House Tyrell of Highgarden" -> "Tyrell"
        "House Baratheon of Dragonstone" -> "Baratheon"
        "House Nymeros Martell of Sunspear" -> "Martell"
        else -> "Stark" // Старки рулят
    }

    private fun String.toColors(): Int = when (this) {
        "House Stark of Winterfell" -> R.color.stark_primary
        "House Lannister of Casterly Rock" -> R.color.lannister_primary
        "House Targaryen of King's Landing" -> R.color.targaryen_primary
        "House Greyjoy of Pyke" -> R.color.greyjoy_primary
        "House Tyrell of Highgarden" -> R.color.tyrel_primary
        "House Baratheon of Dragonstone" -> R.color.baratheon_primary
        "House Nymeros Martell of Sunspear" -> R.color.martel_primary
        else -> R.color.stark_primary // Старки рулят
    }
}