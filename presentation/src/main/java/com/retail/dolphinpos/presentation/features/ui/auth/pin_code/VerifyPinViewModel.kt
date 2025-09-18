package com.retail.dolphinpos.presentation.features.ui.auth.pin_code

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retail.dolphinpos.common.PreferenceManager
import com.retail.dolphinpos.domain.repositories.VerifyPinRepository
import com.retail.dolphinpos.domain.usecases.GetCurrentTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyPinViewModel @Inject constructor(
    private val repository: VerifyPinRepository,
    private val preferenceManager: PreferenceManager,
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase
) : ViewModel() {
    private val _currentTime = MutableStateFlow("")
    val currentTime: StateFlow<String> = _currentTime

    private val _currentDate = MutableStateFlow("")
    val currentDate: StateFlow<String> = _currentDate

    private val _verifyPinUiEvent = MutableLiveData<VerifyPinUiEvent>()
    val verifyPinUiEvent: LiveData<VerifyPinUiEvent> = _verifyPinUiEvent

    init {
        viewModelScope.launch {
            getCurrentTimeUseCase().collect { (time, date) ->
                _currentDate.value = date
                _currentTime.value = time
            }
        }
    }

    fun verifyPin(
        pin: String
    ) {
        viewModelScope.launch {
            _verifyPinUiEvent.value = VerifyPinUiEvent.ShowLoading
            try {
                val user = repository.getCompleteUserData(pin)
                if (user == null) _verifyPinUiEvent.value =
                    VerifyPinUiEvent.ShowError("No record exists against this PIN")
                else {
                    Log.e("User", user.toString())
                    _verifyPinUiEvent.value = VerifyPinUiEvent.NavigateToCashDenomination
                }

                _verifyPinUiEvent.value = VerifyPinUiEvent.HideLoading

            } catch (e: Exception) {
                _verifyPinUiEvent.value = VerifyPinUiEvent.HideLoading
                _verifyPinUiEvent.value =
                    VerifyPinUiEvent.ShowError(e.message ?: "Something went wrong")
            }
        }
    }
}