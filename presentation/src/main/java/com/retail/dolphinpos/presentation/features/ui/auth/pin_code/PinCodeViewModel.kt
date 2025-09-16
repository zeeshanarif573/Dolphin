package com.retail.dolphinpos.presentation.features.ui.auth.pin_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retail.dolphinpos.domain.usecases.GetCurrentTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
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

    fun verifyPin(pin: String) {
//        viewModelScope.launch {
//            _verifyPinUiEvent.value = VerifyPinUiEvent.ShowLoading
//            try {
//                val response = repository.login(LoginRequest(username, password))
//                _loginUiEvent.value = LoginUiEvent.HideLoading
//
//                response.loginData?.let { loginData ->
//                    setPreferences(loginData)
//                    repository.insertLoginDataIntoLocalDB(loginData, password)
//                    _loginUiEvent.value = LoginUiEvent.NavigateToRegister
//
//                } ?: run {
//                    _loginUiEvent.value =
//                        LoginUiEvent.ShowError(response.message ?: "No data received")
//                }
//            } catch (e: Exception) {
//                _loginUiEvent.value = LoginUiEvent.HideLoading
//                _loginUiEvent.value = LoginUiEvent.ShowError(e.message ?: "Something went wrong")
//            }
//        }
    }
}