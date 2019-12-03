package ru.skillbranch.gameofthrones

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

abstract class ValueTransmitter<T> {

    private val valuesPublishSubject: PublishSubject<T> = PublishSubject.create()

    private val valuesBehaviorSubject: BehaviorSubject<T> = BehaviorSubject.create()

    val listenValues: Observable<T> = valuesPublishSubject.hide()

    val getLastAndListen: Observable<T> = valuesBehaviorSubject.hide()

    fun accept(newValue: T) {
        valuesPublishSubject.onNext(newValue)
        valuesBehaviorSubject.onNext(newValue)
    }
}