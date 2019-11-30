package ru.skillbranch.gameofthrones.ui

import ru.skillbranch.gameofthrones.base.BaseViewModel
import ru.skillbranch.gameofthrones.repositories.RootRepository
import ru.skillbranch.gameofthrones.routers.CharacterScreenRouter

class CharacterScreenViewModel(
    private val router: CharacterScreenRouter,
    private val repository: RootRepository
) : BaseViewModel()