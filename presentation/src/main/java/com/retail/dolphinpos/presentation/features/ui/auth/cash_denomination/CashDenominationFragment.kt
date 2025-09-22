package com.retail.dolphinpos.presentation.features.ui.auth.cash_denomination

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.FragmentCashDenominationBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment
import com.retail.dolphinpos.presentation.features.base.setOnSafeClickListener

class CashDenominationFragment :
    BaseFragment<FragmentCashDenominationBinding>(FragmentCashDenominationBinding::inflate) {

    private lateinit var keyPadButtons: List<Pair<Int, String>>
    private lateinit var adapter: TrayAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeKeypadButtons()
        clickEvents()

        val trayList = getCashTrayList()
        adapter = TrayAdapter(trayList)
        binding.trayItemView.adapter = adapter
    }

    private fun clickEvents() {
        binding.saveAndStartBatchButton.setOnSafeClickListener {}

        keyPadButtons.forEach { (id, digit) ->
            view?.findViewById<Button>(id)?.setOnClickListener {

            }
        }
    }

    private fun initializeKeypadButtons() {
        keyPadButtons = listOf(
            R.id.keypad_action_button_zero to getString(R.string._0),
            R.id.keypad_action_button_one to getString(R.string._1),
            R.id.keypad_action_button_two to getString(R.string._2),
            R.id.keypad_action_button_three to getString(R.string._3),
            R.id.keypad_action_button_four to getString(R.string._4),
            R.id.keypad_action_button_five to getString(R.string._5),
            R.id.keypad_action_button_six to getString(R.string._6),
            R.id.keypad_action_button_seven to getString(R.string._7),
            R.id.keypad_action_button_eight to getString(R.string._8),
            R.id.keypad_action_button_nine to getString(R.string._9),
        )
    }

    private fun getCashTrayList() = listOf(
        Tray.Header(getString(R.string.cash_tray)),
        Tray.Item(
            getString(R.string.cash_1),
            getString(R.string.dollar_one),
            1.0,
            0,
            TrayType.CASH,
            isSelected = true
        ),
        Tray.Item(
            getString(R.string.cash_5),
            getString(R.string.dollar_five),
            5.0,
            0,
            TrayType.CASH
        ),
        Tray.Item(
            getString(R.string.cash_10),
            getString(R.string.dollar_ten),
            10.0,
            0,
            TrayType.CASH
        ),
        Tray.Item(
            getString(R.string.cash_20),
            getString(R.string.dollar_twenty),
            20.0,
            0,
            TrayType.CASH
        ),
        Tray.Item(
            getString(R.string.cash_50),
            getString(R.string.dollar_fifty),
            50.0,
            0,
            TrayType.CASH
        ),
        Tray.Item(
            getString(R.string.cash_100),
            getString(R.string.dollar_hundred),
            100.0,
            0,
            TrayType.CASH
        ),

        Tray.Header(getString(R.string.coins_tray)),
        Tray.Item(
            getString(R.string.coin_0_01),
            getString(R.string.cent_one),
            0.01,
            0,
            TrayType.COIN
        ),
        Tray.Item(
            getString(R.string.coin_0_05),
            getString(R.string.cent_five),
            0.05,
            0,
            TrayType.COIN
        ),
        Tray.Item(
            getString(R.string.coin_0_1),
            getString(R.string.cent_ten),
            0.1,
            0,
            TrayType.COIN
        ),
        Tray.Item(
            getString(R.string.coin_0_25),
            getString(R.string.cent_twenty_five),
            0.25,
            0,
            TrayType.COIN
        )
    )

}
