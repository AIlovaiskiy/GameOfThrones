package ru.skillbranch.gameofthrones.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.skillbranch.gameofthrones.data.local.entities.toHousesColor
import ru.skillbranch.gameofthrones.data.local.entities.toShortName
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

    fun getColor(position: Int): Int = housesName[position].toHousesColor()


}