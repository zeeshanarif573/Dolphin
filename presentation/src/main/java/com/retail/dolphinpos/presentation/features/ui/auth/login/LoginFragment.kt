package com.retail.dolphinpos.presentation.features.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.FragmentLoginBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment
import com.retail.dolphinpos.presentation.features.base.setOnSafeClickListener
import com.retail.dolphinpos.presentation.util.Utils
import com.retail.dolphinpos.presentation.util.Utils.setEnabledWithAlpha
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateButtonState()
        clickEvents()
        eventObserver()
    }

    private fun clickEvents() {
        binding.loginActionButton.setOnSafeClickListener {
            viewModel.login("schaumburg", "schaumburg")
        }

        binding.etUsername.doOnTextChanged { _, _, _, _ -> updateButtonState() }
        binding.etPassword.doOnTextChanged { _, _, _, _ -> updateButtonState() }
    }

    private fun eventObserver() {
        viewModel.loginUiEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is LoginUiEvent.ShowLoading -> Utils.showLoader(requireContext())
                is LoginUiEvent.HideLoading -> Utils.hideLoader()
                is LoginUiEvent.ShowError -> Utils.showErrorDialog(requireContext(), message = event.message)
                is LoginUiEvent.NavigateToRegister -> {
                    findNavController().navigate(R.id.action_loginFragment_to_selectRegisterFragment2)
                }
            }
        }
    }

    private fun updateButtonState() {
        val isEnabled = binding.etUsername.text?.isNotEmpty() == true &&
                binding.etPassword.text?.isNotEmpty() == true

        binding.loginActionButton.setEnabledWithAlpha(isEnabled)
    }

}