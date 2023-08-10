package com.ultra.sample.main.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ultra.sample.R

sealed class Tab(
    val route: String,
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
) {

    object Products : Tab("products", R.string.products, R.drawable.ic_tab_products)
    object Payments : Tab("payments", R.string.payments, R.drawable.ic_tab_payments)
    object Expenses : Tab("expenses", R.string.expenses, R.drawable.ic_tab_expenses)
    object Chats : Tab("chats", R.string.chats, R.drawable.ic_tab_chats)
}