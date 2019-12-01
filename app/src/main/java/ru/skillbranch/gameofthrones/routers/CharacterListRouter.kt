package ru.skillbranch.gameofthrones.routers

import androidx.fragment.app.Fragment
import ru.skillbranch.gameofthrones.ui.CharacterScreenFragment
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CharacterListRouter(private val router: Router) {

    fun goToCharacterFull(characterId: String) {
        router.navigateTo(object : SupportAppScreen() {
            override fun getFragment(): Fragment {
                return CharacterScreenFragment.getInstance(characterId)
            }
        })
    }

    fun back() = router.exit()
}