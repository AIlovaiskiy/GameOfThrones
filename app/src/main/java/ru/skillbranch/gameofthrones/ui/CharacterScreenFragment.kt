package ru.skillbranch.gameofthrones.ui

import android.os.Bundle
import android.view.View
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
        toolbar.title = characterData.name
        wordsValue.text = characterData.words
        bornValue.text = characterData.born
        titlesValue.text = characterData.titles.joinToString(separator = ", ")
        aliasesValue.text = characterData.aliases.joinToString(separator = ", ")
        houseLogo.setImageResource(characterData.house.toHouseLogo())
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


    private fun String.toHouseLogo(): Int = when (this) {
        "House Stark of Winterfell" -> R.drawable.stark_coast_of_arms
        "House Lannister of Casterly Rock" -> R.drawable.lannister__coast_of_arms
        "House Targaryen of King's Landing" -> R.drawable.targaryen_coast_of_arms
        "House Greyjoy of Pyke" -> R.drawable.greyjoy_coast_of_arms
        "House Tyrell of Highgarden" -> R.drawable.targaryen_coast_of_arms
        "House Baratheon of Dragonstone" -> R.drawable.baratheon
        "House Nymeros Martell of Sunspear" -> R.drawable.martel_coast_of_arms
        else -> R.drawable.stark_coast_of_arms // Старки рулят
    }
}