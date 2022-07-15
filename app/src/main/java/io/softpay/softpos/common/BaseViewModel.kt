package io.softpay.softpos.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


abstract class BaseViewModel<T>(
    initialState: T,
) : ViewModel() {

    private val stateFlow = MutableStateFlow(initialState)

    fun state(): StateFlow<T> = stateFlow

    internal fun updateState(newState: T.() -> T) {
        stateFlow.update(newState)
    }

    fun currentState(): T = state().value

}




