package ru.skillbranch.gameofthrones

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class ValueTransmitter {

    private val valuesPublishSubject: PublishSubject<String> = PublishSubject.create()

    private val valuesBehaviorSubject: BehaviorSubject<String> = BehaviorSubject.create()

    val listenValues: Observable<String> = valuesPublishSubject.hide()

    val getLastAndListen: Observable<String> = valuesBehaviorSubject.hide()

    fun accept(newValue: String) {
        valuesPublishSubject.onNext(newValue)
        valuesBehaviorSubject.onNext(newValue)
    }
}