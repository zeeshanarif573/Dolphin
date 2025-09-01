package com.retail.dolphinpos.presentation.features.ui.auth.select_register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.FragmentSelectRegisterBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment
import com.retail.dolphinpos.presentation.features.base.setOnSafeClickListener

class SelectRegisterFragment :
    BaseFragment<FragmentSelectRegisterBinding>(FragmentSelectRegisterBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueActionButton.setOnSafeClickListener {
            findNavController().navigate(R.id.action_selectRegisterFragment_to_pinCodeFragment)
        }
    }
}