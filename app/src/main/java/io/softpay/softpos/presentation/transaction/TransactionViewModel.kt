package io.softpay.softpos.presentation.transaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.softpay.sdk.*
import io.softpay.softpos.common.BaseViewModel
import io.softpay.softpos.domain.model.PurchaseModel
import io.softpay.softpos.utils.formatAmount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val transactionManager: TransactionManager
) : BaseViewModel<TransactionViewModel.ScreenState>(ScreenState()) {

    data class ScreenState(
        var isLoading: Boolean = false,
        val isProcessing: Boolean = false,
        val isWaitingAmount: Boolean = false,
        val isAwaitingConfirmation: Boolean = false,
        val isSuccess: Boolean = false
    )

    var amountValue: Long by mutableStateOf(0L)
        private set

    var storeDetails: Store? by mutableStateOf(null)
        private set

    var purchaseModel: PurchaseModel? by mutableStateOf(null)
        private set

    private var currentTransactionFlow: Flow<Transaction>? = null
    private var currentState: State? = null
    private var amountEntered: Long = 0


    init {
        updateAmount()
        viewModelScope.launch {
            try {
                startTransaction()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun startTransaction() {
        if (currentTransactionFlow == null) {
            currentTransactionFlow = transactionManager.newTransactionFlow()
            currentTransactionFlow?.collect {
                currentState = it.state
                updateState {
                    copy(
                        isWaitingAmount = it.state == State.AWAITING_AMOUNT,
                        isProcessing = it.state == State.PROCESSING,
                        isAwaitingConfirmation = it.state == State.AWAITING_CONFIRMATION,
                        isSuccess = it.state == State.SUCCESS
                    )
                }
                it.store?.let { store -> storeDetails = store }
                if (it.state == State.SUCCESS) {
                    purchaseModel = PurchaseModel(
                        name = it.store?.name,
                        address = it.store?.address,
                        postalCode = it.store?.postalCode,
                        referenceId = it.referenceId,
                        amount = amountValue.formatAmount(),
                    )
                }
            }
        }
    }

    private fun updateAmount(amount: Long = 0) {
        viewModelScope.launch {
            amountEntered = amount
            amountValue = amount
        }
    }

    fun enterNumber(digit: Int) {
        if (amountEntered.toString().length > 9) return
        updateAmount(("$amountEntered${digit.toLong()}").toLong())
    }

    fun deletedNumber() {
        val amountEntered = amountEntered.toString()
        val updatedAmount = amountEntered.substring(0, amountEntered.lastIndex)
        updateAmount(if (updatedAmount.isEmpty()) 0 else updatedAmount.toLong())
    }

    fun confirm() {
        if (currentState != State.AWAITING_AMOUNT) return
        viewModelScope.launch {
            try {
                transactionManager.dispatch(Input.Amount(amountEntered))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun cancelTransaction() {
        if (currentState == State.SUCCESS || currentState == State.FAILURE) return
        viewModelScope.launch {
            try {
                transactionManager.dispatch(Input.Cancel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun confirmPurchase(accept: Boolean) {
        viewModelScope.launch {
            try {
                transactionManager.dispatch(Input.Confirm(accept))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}