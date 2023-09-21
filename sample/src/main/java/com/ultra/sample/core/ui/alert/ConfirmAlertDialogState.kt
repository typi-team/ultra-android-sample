package com.ultra.sample.core.ui.alert

import androidx.annotation.StringRes
import com.ultra.sample.R

data class ConfirmAlertDialogState(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @StringRes val confirmButtonText: Int = R.string.yes,
    @StringRes val dismissButtonText: Int = R.string.cancel
)
