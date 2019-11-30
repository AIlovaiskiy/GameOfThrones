package ru.skillbranch.gameofthrones.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.IO) {
    override fun onCleared() {
        super.onCleared()
        cancel()
    }
}