package com.retail.dolphinpos.presentation.features.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.retail.dolphinpos.common.PreferenceManager
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.FragmentLoginBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment
import com.retail.dolphinpos.presentation.features.base.setOnSafeClickListener
import com.retail.dolphinpos.presentation.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvents()
        loginResponseObserver()
    }

    private fun clickEvents() {
        binding.loginActionButton.setOnSafeClickListener {
            viewModel.login("schaumburg", "schaumburg")
        }
    }

    private fun loginResponseObserver() {
        viewModel.loginUiEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is LoginUiEvent.ShowLoading -> Utils.showLoader(requireContext())
                is LoginUiEvent.HideLoading -> Utils.hideLoader()
                is LoginUiEvent.ShowError -> Utils.showErrorDialog(requireContext(), event.message)
                is LoginUiEvent.NavigateToRegister -> {
                    findNavController().navigate(R.id.action_loginFragment_to_selectRegisterFragment2)
                }
            }
        }
    }

}