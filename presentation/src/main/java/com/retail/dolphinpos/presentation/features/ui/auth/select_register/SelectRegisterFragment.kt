package com.retail.dolphinpos.presentation.features.ui.auth.select_register

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.FragmentSelectRegisterBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment
import com.retail.dolphinpos.presentation.features.base.setOnSafeClickListener
import com.retail.dolphinpos.presentation.util.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectRegisterFragment :
    BaseFragment<FragmentSelectRegisterBinding>(FragmentSelectRegisterBinding::inflate) {

    private val viewModel by viewModels<SelectRegisterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeRegistersObserver()
        eventObserver()
        clickEvents()
    }

    private fun clickEvents() {
        binding.continueActionButton.setOnSafeClickListener {

        }

        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = false
            viewModel.getStoreRegisters()
        }
    }

    private fun storeRegistersObserver() {
        viewModel.storeRegisters.observe(viewLifecycleOwner) { list ->
            if (!list.isNullOrEmpty()) {
                binding.tvSelectRegisterHint.visibility = GONE
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    list.map { it.name }
                )
                adapter.setDropDownViewResource(R.layout.item_dropdown)
                binding.spRegisters.adapter = adapter
            } else {
                binding.tvSelectRegisterHint.visibility = VISIBLE
                Utils.showErrorDialog(
                    requireContext(), message = getString(R.string.registers_in_use)
                )
            }
        }
    }

    private fun eventObserver() {
        viewModel.selectRegisterUiEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                SelectRegisterUiEvent.ShowLoading -> Utils.showLoader(requireContext())
                SelectRegisterUiEvent.HideLoading -> Utils.hideLoader()
                SelectRegisterUiEvent.NavigateToPinScreen -> findNavController().navigate(R.id.action_selectRegisterFragment_to_pinCodeFragment)
                is SelectRegisterUiEvent.ShowError -> Utils.showErrorDialog(
                    requireContext(), message = event.message
                )
            }
        }
    }
}