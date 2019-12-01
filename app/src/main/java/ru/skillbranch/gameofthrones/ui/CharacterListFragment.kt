package ru.skillbranch.gameofthrones.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.house_page_layout.*
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.base.BaseFragment
import ru.skillbranch.gameofthrones.ui.adapters.CharactersItemAdapter

const val HOUSE_NAME = "house_name"

class CharacterListFragment : BaseFragment<CharacterListViewModel>() {
    override val layoutRes: Int = R.layout.house_page_layout

    override fun subscribeToViewModel() {

        viewModel
            .characters
            .observe(viewLifecycleOwner, Observer { charactersData ->
                (characters.adapter as CharactersItemAdapter).submitItem(charactersData)
            })
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        characters.adapter = CharactersItemAdapter()
    }


    companion object {

        fun getInstance(houseName: String): CharacterListFragment = CharacterListFragment()
            .apply {
                arguments = Bundle().also {
                    it.putString(HOUSE_NAME, houseName)
                }
            }
    }
}