package io.softpay.softpos.data.remote

import androidx.annotation.StringRes

interface StringProvider {

    fun getString(@StringRes id: Int): String

}