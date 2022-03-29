package io.softpay.sdk

import kotlinx.coroutines.flow.Flow

interface TransactionManager {

    fun newTransactionFlow(): Flow<Transaction>

    fun dispatch(input: Input)
}

data class Transaction(
    val referenceId: String?,
    val amount: Long?,
    val state: State,
    val store: Store?,
    val isFinal: Boolean
)

data class Store(
    val name: String,
    val address: String,
    val postalCode: String
)

enum class State {
    AWAITING_AMOUNT, PROCESSING, AWAITING_CONFIRMATION, SUCCESS, FAILURE, CANCELLED
}

sealed class Input {

    data class Amount(
        val value: Long
    ) : Input()

    data class Confirm(
        val value: Boolean
    ) : Input()

    object Cancel : Input()
}