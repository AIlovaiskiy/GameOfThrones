package ru.skillbranch.gameofthrones

import ru.skillbranch.gameofthrones.base.BaseViewModel
import ru.skillbranch.gameofthrones.repositories.RootRepository
import ru.skillbranch.gameofthrones.routers.CharacterScreenRouter
import ru.skillbranch.gameofthrones.routers.MainActivityRouter

class MainActivityViewModel(
    private val router: MainActivityRouter,
    private val repository: RootRepository
) : BaseViewModel()