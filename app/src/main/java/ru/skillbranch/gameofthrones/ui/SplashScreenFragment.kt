package ru.skillbranch.gameofthrones.ui

import android.os.Bundle
import android.view.View
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.base.BaseFragment

class SplashScreenFragment : BaseFragment<SplashScreenViewModel>() {
    override val layoutRes: Int
        get() = R.layout.splash_screen_layout

    override fun subscribeToViewModel() {
    }

    override fun setupViews(view: View, savedInstanceState: Bundle?) {
    }


    companion object {
        fun getInstance(): SplashScreenFragment {
            return SplashScreenFragment()
        }
    }


}