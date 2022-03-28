package io.softpay.sdk.impl

import io.softpay.sdk.Input
import io.softpay.sdk.State
import io.softpay.sdk.Transaction
import io.softpay.sdk.TransactionManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

class TransactionManagerImpl : TransactionManager, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    private var stateFlow: MutableStateFlow<Transaction>? = null

    override fun newTransactionFlow(): Flow<Transaction> {
        val currentFlow = stateFlow
        check(currentFlow == null || currentFlow.value.isFinal) { "There is already an ongoing transaction!" }
        return MutableStateFlow(
            Transaction(
                amount = null,
                state = State.PROCESSING,
                isFinal = false
            )
        ).also {
            stateFlow = it
            launch {
                delay(1000)
                it.update { copy(state = State.AWAITING_AMOUNT) }
            }
        }
    }

    override fun dispatch(input: Input) {
        val currentFlow = stateFlow
        checkNotNull(currentFlow) { "Start a transaction first!" }
        when (input) {
            is Input.Amount -> {
                launch {
                    require(currentFlow.value.state == State.AWAITING_AMOUNT) { "The flow is not awaiting an amount!" }
                    delay(1000)
                    currentFlow.update { copy(amount = input.value, state = State.PROCESSING) }
                    delay(1000)
                    currentFlow.update { copy(state = State.AWAITING_CONFIRMATION) }
                }
            }
            is Input.Confirm -> {
                launch {
                    require(currentFlow.value.state == State.AWAITING_CONFIRMATION) { "The flow is not awaiting confirmation!" }
                    delay(1000)
                    currentFlow.update { copy(state = State.PROCESSING) }
                    delay(1000)
                    currentFlow.update {
                        copy(
                            isFinal = true,
                            state = if (input.value) State.SUCCESS else State.FAILURE
                        )
                    }
                }
            }
            Input.Cancel -> {
                require(currentFlow.value.isFinal.not()) { "The flow is in Final state!" }
                currentFlow.update { copy(isFinal = true, state = State.CANCELLED) }
                cancel("Flow is canceled")
            }
        }
    }

    private fun MutableStateFlow<Transaction>.update(block: Transaction.() -> Transaction) {
        value = block(value)
    }
}