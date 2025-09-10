package com.retail.dolphinpos.presentation.features.ui.auth.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.retail.dolphinpos.common.PreferenceManager
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.FragmentSplashBinding
import com.retail.dolphinpos.presentation.features.base.BaseFragment
import com.retail.dolphinpos.presentation.features.base.setOnSafeClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    @Inject
    lateinit var preferenceManager: PreferenceManager
    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvents()

        // Collect current time
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentTime.collect { time ->
                    binding.currentTime.text = time
                }
            }
        }

        // Collect current date
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentDate.collect { date ->
                    binding.currentDate.text = date
                }
            }
        }
    }

    private fun clickEvents() {
        binding.letsStartActionButton.setOnSafeClickListener {
            if (preferenceManager.getRegister())
                findNavController().navigate(R.id.action_splashFragment_to_pinCodeFragment)
            else
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}