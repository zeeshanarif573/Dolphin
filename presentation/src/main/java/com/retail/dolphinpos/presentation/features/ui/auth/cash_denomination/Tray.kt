package com.retail.dolphinpos.presentation.features.ui.auth.cash_denomination

sealed class Tray {
    data class Header(val title: String) : Tray()
    data class Item(
        val id: String,             // Unique ID like "CASH_1", "COIN_0.01"
        val label: String,          // "$1", "$0.01"
        val value: Double,          // 1.0, 0.01
        var count: Int = 0,         // Count value
        val type: TrayType,          // CASH or COIN
        val isSelected: Boolean = false
    ) : Tray()
}

enum class TrayType {
    CASH, COIN
}