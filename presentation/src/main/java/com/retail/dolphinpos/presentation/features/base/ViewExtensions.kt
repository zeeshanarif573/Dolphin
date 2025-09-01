package com.retail.dolphinpos.presentation.features.base

import android.view.View

fun View.setOnSafeClickListener(
    cooldownTime: Long = 1000L,
    onSafeClick: (View) -> Unit
) {
    setOnClickListener(CooldownClickListener(cooldownTime, onSafeClick))
}