package com.retail.dolphinpos.presentation.features.ui.auth.cash_denomination

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.FragmentCashDenominationBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment
import com.retail.dolphinpos.presentation.features.base.setOnSafeClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class CashDenominationFragment :
    BaseFragment<FragmentCashDenominationBinding>(FragmentCashDenominationBinding::inflate) {

    private val viewModel: CashDenominationViewModel by viewModels()
    private lateinit var keyPadButtons: List<Pair<Int, String>>
    private lateinit var adapter: CashDenominationAdapter
    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeKeypadButtons()
        setupRecyclerView()
        setupClickEvents()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = CashDenominationAdapter { denomination ->
            viewModel.selectDenomination(denomination)
        }
        binding.trayItemView.adapter = adapter
    }

    private fun setupClickEvents() {
        binding.saveAndStartBatchButton.setOnSafeClickListener {
            // TODO: Implement save and start batch functionality
        }

        binding.openCashDrawerButton.setOnSafeClickListener {
            // TODO: Implement open cash drawer functionality
        }

        binding.keypadActionButtonClear.setOnSafeClickListener {
            viewModel.clearCount()
        }

        binding.keypadActionButtonDoubleZero.setOnSafeClickListener {
            viewModel.addDoubleZero()
        }

        keyPadButtons.forEach { (id, digit) ->
            view?.findViewById<Button>(id)?.setOnClickListener {
                viewModel.addDigit(digit)
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

    private fun observeViewModel() {
        // Observe denominations and update adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.denominations.collect { denominations ->
                adapter.updateDenominations(denominations)
            }
        }

        // Observe total amount and update UI
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.totalAmount.collect { total ->
                binding.cashValue.text = currencyFormatter.format(total)
            }
        }

        // Observe current count and update counter display
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentCount.collect { count ->
                binding.startBatchAmount.setText(count)
            }
        }

        // Observe selected denomination
        viewModel.selectedDenomination.observe(viewLifecycleOwner) { denomination ->
            if (denomination != null) {
                // Update UI to show selected denomination
                binding.counterLabel.text = "Count for ${denomination.label}"
            }
            // Update adapter to highlight selected denomination
            adapter.updateSelectedDenomination(denomination)
        }
    }
}
