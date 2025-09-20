package com.retail.dolphinpos.presentation.features.ui.auth.select_register

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.retail.dolphinpos.domain.model.auth.login.response.Locations
import com.retail.dolphinpos.domain.model.auth.login.response.Registers
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
    var locationID = 0
    var storeRegisterID = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewTitle.text = "Welcome ${viewModel.getName()}"
        eventObserver()
        clickEvents()
    }

    private fun clickEvents() {
        binding.continueActionButton.setOnSafeClickListener {
            viewModel.updateStoreRegister(locationID, storeRegisterID)
        }

        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = false
        }

        binding.logout.setOnSafeClickListener {
            viewModel.logout()
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
            })
    }

    private fun eventObserver() {
        viewModel.selectRegisterUiEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                SelectRegisterUiEvent.ShowLoading -> Utils.showLoader(requireContext())
                SelectRegisterUiEvent.HideLoading -> Utils.hideLoader()
                SelectRegisterUiEvent.NavigateToPinScreen -> findNavController().navigate(R.id.action_selectRegisterFragment_to_pinCodeFragment)
                SelectRegisterUiEvent.NavigateToLoginScreen -> findNavController().navigate(R.id.action_selectRegisterFragment_to_loginFragment)
                is SelectRegisterUiEvent.ShowError -> Utils.showErrorDialog(
                    requireContext(), message = event.message
                )

                is SelectRegisterUiEvent.PopulateLocationsList -> {
                    binding.tvSelectLocationHint.visibility =
                        if (event.locationsList.isNotEmpty()) GONE else VISIBLE

                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        event.locationsList.map { it.name })
                    adapter.setDropDownViewResource(R.layout.item_dropdown)
                    binding.spLocations.adapter = adapter

                    binding.spLocations.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>, view: View?, position: Int, id: Long
                            ) {
                                val selectedLocation: Locations = event.locationsList[position]
                                locationID = selectedLocation.id
                                viewModel.getStoreRegisters(locationID)
                            }

                            override fun onNothingSelected(parent: AdapterView<*>) {
                                // handle if needed
                            }
                        }
                }

                is SelectRegisterUiEvent.PopulateRegistersList -> {
                    binding.tvSelectRegisterHint.visibility =
                        if (event.registersList.isNotEmpty()) GONE else VISIBLE

                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        event.registersList.map { it.name })
                    adapter.setDropDownViewResource(R.layout.item_dropdown)
                    binding.spRegisters.adapter = adapter

                    binding.spRegisters.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>, view: View?, position: Int, id: Long
                            ) {
                                val selectedRegister: Registers = event.registersList[position]
                                storeRegisterID = selectedRegister.id
                            }

                            override fun onNothingSelected(parent: AdapterView<*>) {
                                // handle if needed
                            }
                        }
                }
            }
        }
    }

}