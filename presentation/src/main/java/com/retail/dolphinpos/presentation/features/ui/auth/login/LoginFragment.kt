package com.retail.dolphinpos.presentation.features.ui.auth.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.FragmentLoginBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment
import com.retail.dolphinpos.presentation.features.base.setOnSafeClickListener

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginActionButton.setOnSafeClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_rselectRegisterFragment2)
        }
    }
}