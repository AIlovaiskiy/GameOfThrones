package ru.skillbranch.gameofthrones

import ru.skillbranch.gameofthrones.base.BaseViewModel
import ru.skillbranch.gameofthrones.routers.MainActivityRouter

class MainActivityViewModel(
    private val router: MainActivityRouter
) : BaseViewModel() {


    fun back() = router.back()

    fun goToSplashScreen() = router.goToSplashScreen()
}