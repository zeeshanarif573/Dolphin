package com.retail.dolphinpos.presentation.features.ui.auth.cash_denomination

import android.os.Bundle
import android.view.View
import com.retail.dolphinpos.presentation.databinding.FragmentCashDenominationBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment
import com.retail.dolphinpos.presentation.features.base.setOnSafeClickListener

class CashDenominationFragment :
    BaseFragment<FragmentCashDenominationBinding>(FragmentCashDenominationBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvents()
    }

    private fun clickEvents() {
        binding.saveAndStartBatchButton.setOnSafeClickListener {}
    }
}