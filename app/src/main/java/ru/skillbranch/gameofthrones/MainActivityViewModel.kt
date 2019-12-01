package ru.skillbranch.gameofthrones

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.skillbranch.gameofthrones.base.BaseViewModel
import ru.skillbranch.gameofthrones.repositories.GameOfThroneRepository
import ru.skillbranch.gameofthrones.routers.MainActivityRouter

class MainActivityViewModel(
    private val router: MainActivityRouter,
    private val repository: GameOfThroneRepository
) : BaseViewModel() {


    fun back() = router.back()

    fun handleNavigation() {
        launch {
            val isNeed = repository.isNeedUpdate()
            withContext(Dispatchers.Main) {
                if (isNeed) {
                    router.goToSplashScreen()
                } else {
                    router.goToCharactersList()
                }
            }
        }
    }
}