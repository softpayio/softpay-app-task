package io.softpay.softpos.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PurchaseModel(
    val name: String? = "",
    val address: String? = "",
    val postalCode: String? = "",
    val referenceId: String? = "",
    val amount: String? = "",
):Parcelable