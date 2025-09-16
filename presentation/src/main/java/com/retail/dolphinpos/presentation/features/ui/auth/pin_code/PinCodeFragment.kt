package com.retail.dolphinpos.presentation.features.ui.auth.pin_code

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.FragmentPinCodeBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment
import com.retail.dolphinpos.presentation.features.base.setOnSafeClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PinCodeFragment : BaseFragment<FragmentPinCodeBinding>(FragmentPinCodeBinding::inflate) {
    private val viewModel by viewModels<PinCodeViewModel>()
    private lateinit var keyPadButtons: List<Pair<Int, String>>
    private var pinBuilder = ""
    private val pinLimit = 4

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        initializeKeypadButtons()
        clickEvents()

        // Collect current time
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentTime.collect { time ->
                    binding.tvCurrentTime.text = time
                }
            }
        }

        // Collect current date
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentDate.collect { date ->
                    binding.tvCurrentDate.text = date
                }
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

    private fun clickEvents() {
        keyPadButtons.forEach { (id, digit) ->
            view?.findViewById<Button>(id)?.setOnClickListener {
                if (pinBuilder.length < pinLimit) {
                    pinBuilder += digit
                    binding.pinText.setText(pinBuilder)
                }
            }
        }

        binding.keypadActionButtonClear.setOnSafeClickListener {
            Log.e("keypadActionButtonClear", "Clicked")
            if (pinBuilder.isNotEmpty()) {
                pinBuilder = pinBuilder.dropLast(1)
                binding.pinText.setText(pinBuilder)
            }
        }

        binding.keypadActionButtonNext.setOnSafeClickListener {
            findNavController().navigate(R.id.action_pinCodeFragment_to_cashDenominationFragment)
        }
    }

    private fun hideKeyboard(){
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}