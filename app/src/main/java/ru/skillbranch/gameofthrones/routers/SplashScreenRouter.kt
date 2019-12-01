package ru.skillbranch.gameofthrones.routers

import androidx.fragment.app.Fragment
import ru.skillbranch.gameofthrones.ui.CharactersListScreenFragment
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SplashScreenRouter(private val router : Router){

    fun goToCharactersList() {
        router.navigateTo(object : SupportAppScreen() {
            override fun getFragment(): Fragment {
                return CharactersListScreenFragment.getInstance()
            }
        })
    }

    fun back() = router.exit()
}