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
import androidx.viewpager2.widget.ViewPager2
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

    private val onPageChangeListener: ViewPager2.OnPageChangeCallback by lazy {
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val color = (housePager.adapter as HousesPageAdapter).getColor(position)
                tabsLayout.background = ColorDrawable(ContextCompat.getColor(context!!, color))
                toolbar.background = (ColorDrawable(ContextCompat.getColor(context!!, color)))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).setSupportBar(toolbar)
        with(housePager) {
            adapter = HousesPageAdapter(childFragmentManager, lifecycle)
            offscreenPageLimit = 3
        }
        TabLayoutMediator(tabsLayout, housePager, true) { tab, position ->
            tab.text = (housePager.adapter as HousesPageAdapter).getTitle(position)
        }.attach()
        housePager.registerOnPageChangeCallback(onPageChangeListener)
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

    override fun onDestroy() {
        super.onDestroy()
        housePager.unregisterOnPageChangeCallback(onPageChangeListener)
    }

    companion object {
        fun getInstance(): CharactersListScreenFragment {
            return CharactersListScreenFragment()
        }
    }
}