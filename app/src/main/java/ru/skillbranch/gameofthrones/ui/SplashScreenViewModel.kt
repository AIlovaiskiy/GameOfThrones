package ru.skillbranch.gameofthrones.ui

import ru.skillbranch.gameofthrones.base.BaseViewModel
import ru.skillbranch.gameofthrones.repositories.RootRepository
import ru.skillbranch.gameofthrones.routers.CharacterScreenRouter
import ru.skillbranch.gameofthrones.routers.SplashScreenRouter

class SplashScreenViewModel(
    private val router: SplashScreenRouter,
    private val repository: RootRepository
) : BaseViewModel()