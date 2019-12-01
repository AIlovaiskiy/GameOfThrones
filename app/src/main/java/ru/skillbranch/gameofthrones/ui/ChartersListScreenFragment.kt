package ru.skillbranch.gameofthrones.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.characters_list_screen_layout.*
import ru.skillbranch.gameofthrones.MainActivity
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.base.BaseFragment
import ru.skillbranch.gameofthrones.ui.adapters.HousesPageAdapter


class CharactersListScreenFragment : BaseFragment<CharactersListScreenViewModel>() {
    override val layoutRes: Int = R.layout.characters_list_screen_layout

    override fun subscribeToViewModel() {
        viewModel
            .pages
            .observe(viewLifecycleOwner, Observer { data ->
                (housePager.adapter as HousesPageAdapter).submitItem(data)
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).setBar(toolbar)
        housePager.adapter = HousesPageAdapter(childFragmentManager, lifecycle)
        tabsLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                val color = (housePager.adapter as HousesPageAdapter).getColor(tab.position)
                tabsLayout.background = ColorDrawable(ContextCompat.getColor(context!!, color))
                toolbar.background = (ColorDrawable(ContextCompat.getColor(context!!, color)))
            }
        })

        TabLayoutMediator(tabsLayout, housePager, true) { tab, position ->
            tab.text = (housePager.adapter as HousesPageAdapter).getTitle(position)
        }.attach()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val search: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = search.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchTextHandler(newText)
                return true
            }

        })
        searchView.setOnQueryTextFocusChangeListener { _, isFocused ->
            if (!isFocused) viewModel.endSearch()
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        fun getInstance(): CharactersListScreenFragment {
            return CharactersListScreenFragment()
        }
    }
}