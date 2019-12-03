package ru.skillbranch.gameofthrones.ui

import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
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
import ru.skillbranch.gameofthrones.data.local.entities.toHouseLogo
import ru.skillbranch.gameofthrones.data.local.entities.toHousesColor

const val CHARACTER_ID = "CHARACTER_ID"

class CharacterScreenFragment : BaseFragment<CharacterScreenViewModel>() {
    override val layoutRes: Int = R.layout.character_screen_layout

    override fun subscribeToViewModel() {
        viewModel
            .characterData
            .observe(viewLifecycleOwner, Observer(::bindData))
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).setSupportBar(toolbar)
        toolbar.setNavigationOnClickListener {
            viewModel.back()
        }
    }

    private fun bindData(characterData: CharacterFull) {
        val logo = characterData.house.toHouseLogo()
        val color = characterData.house.toHousesColor()
        toolbar.title = characterData.name
        toolbar.background = (ColorDrawable(ContextCompat.getColor(context!!, color)))
        words.setDrawableColor(color)
        born.setDrawableColor(color)
        titles.setDrawableColor(color)
        aliases.setDrawableColor(color)
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
}