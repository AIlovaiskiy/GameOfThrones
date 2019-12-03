package ru.skillbranch.gameofthrones.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.character_screen_layout.*
import ru.skillbranch.gameofthrones.MainActivity
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.base.BaseFragment
import ru.skillbranch.gameofthrones.data.local.entities.CharacterFull

const val CHARACTER_ID = "CHARACTER_ID"

class CharacterScreenFragment : BaseFragment<CharacterScreenViewModel>() {
    override val layoutRes: Int = R.layout.character_screen_layout

    override fun subscribeToViewModel() {
        viewModel
            .characterData
            .observe(viewLifecycleOwner, Observer(::bindData))
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).setBar(toolbar)
        toolbar.setNavigationOnClickListener {
            viewModel.back()
        }
    }

    private fun bindData(characterData: CharacterFull) {
        val (logo, backgroundColor) = characterData.house.toHouseLogoAndColor()
        toolbar.title = characterData.name
        words.setDrawableColor(backgroundColor)
        born.setDrawableColor(backgroundColor)
        titles.setDrawableColor(backgroundColor)
        aliases.setDrawableColor(backgroundColor)
        wordsValue.text = characterData.words
        bornValue.text = characterData.born
        titlesValue.text = characterData.titles.joinToString(separator = ", ")
        aliasesValue.text = characterData.aliases.joinToString(separator = ", ")
        houseLogo.setImageResource(logo)
        characterData.father?.let { parent ->
            father.visibility = View.VISIBLE
            fatherButton.visibility = View.VISIBLE
            fatherButton.text = parent.name
            fatherButton.setOnClickListener { viewModel.parentClick(parent.id) }
        } ?: let {
            father.visibility = View.GONE
            fatherButton.visibility = View.GONE
        }

        characterData.mother?.let { parent ->
            mother.visibility = View.VISIBLE
            motherButton.visibility = View.VISIBLE
            motherButton.text = parent.name
            motherButton.setOnClickListener { viewModel.parentClick(parent.id) }
        } ?: let {
            mother.visibility = View.GONE
            motherButton.visibility = View.GONE
        }
        view?.let { onSnack(it, characterData.died) }
    }


    companion object {

        fun getInstance(characterId: String): CharacterScreenFragment =
            CharacterScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(CHARACTER_ID, characterId)
                }
            }
    }

    private fun onSnack(view: View, text: String) {
        if (text.isEmpty()) return
        Snackbar
            .make(view, text, Snackbar.LENGTH_INDEFINITE)
            .show()
    }

    private fun TextView.setDrawableColor(colorResId: Int) {
        val icon = this.compoundDrawables[0]
        val color = ContextCompat.getColor(this.context, colorResId)
        icon?.let { it.setColorFilter(color, PorterDuff.Mode.SRC_IN) }
    }


    private fun String.toHouseLogoAndColor(): Pair<Int, Int> = when (this) {
        "House Stark of Winterfell" -> R.drawable.stark_coast_of_arms to R.color.stark_accent
        "House Lannister of Casterly Rock" -> R.drawable.lannister__coast_of_arms to R.color.lannister_accent
        "House Targaryen of King's Landing" -> R.drawable.targaryen_coast_of_arms to R.color.targaryen_accent
        "House Greyjoy of Pyke" -> R.drawable.greyjoy_coast_of_arms to R.color.greyjoy_accent
        "House Tyrell of Highgarden" -> R.drawable.targaryen_coast_of_arms to R.color.tyrel_accent
        "House Baratheon of Dragonstone" -> R.drawable.baratheon to R.color.baratheon_primary
        "House Nymeros Martell of Sunspear" -> R.drawable.martel_coast_of_arms to R.color.martel_primary
        else -> R.drawable.stark_coast_of_arms to R.color.stark_accent  // Старки рулят
    }
}