package ru.skillbranch.gameofthrones.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.character_item_layout.view.*
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem


class CharactersItemAdapter : RecyclerView.Adapter<CharacterVH>() {
    private var characters: List<CharacterItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        return CharacterVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.character_item_layout,
                parent,
                false
            )
        )
    }


    fun submitItem(items: List<CharacterItem>) {
        characters = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        val characterItem = characters[position]
        holder.itemView.run {
            val titles = characterItem.titles.joinToString(separator = ", ")
            val name = characterItem.name
            characterName.text = if (name.isEmpty()) "Not named(veru bad api)" else name
            characterTitles.text = if (titles.isEmpty()) "Information unknown" else titles
            houseImage.setImageResource(characterItem.house.getHouseIcon())
            setOnClickListener { characterItem.clickListener(characterItem.id) }
        }

    }

    @DrawableRes
    private fun String.getHouseIcon(): Int = when (this) {
        "House Stark of Winterfell" -> R.drawable.stark_icon
        "House Lannister of Casterly Rock" -> R.drawable.lanister_icon
        "House Targaryen of King's Landing" -> R.drawable.targaryen_icon
        "House Greyjoy of Pyke" -> R.drawable.greyjoy_icon
        "House Tyrell of Highgarden" -> R.drawable.tyrel_icon
        "House Baratheon of Dragonstone" -> R.drawable.baratheon_icon
        "House Nymeros Martell of Sunspear" -> R.drawable.martel_icon
        else -> R.drawable.stark_icon // Старки рулят
    }
}

class CharacterVH(itemView: View) : RecyclerView.ViewHolder(itemView)