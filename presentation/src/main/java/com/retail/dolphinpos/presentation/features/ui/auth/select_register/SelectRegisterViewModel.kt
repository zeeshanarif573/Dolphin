package com.retail.dolphinpos.presentation.features.ui.auth.select_register

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retail.dolphinpos.common.PreferenceManager
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.GetStoreRegistersData
import com.retail.dolphinpos.domain.model.auth.select_registers.request.UpdateStoreRegisterRequest
import com.retail.dolphinpos.domain.repositories.StoreRegistersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectRegisterViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val storeRegistersRepository: StoreRegistersRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _selectRegisterUiEvent = MutableLiveData<SelectRegisterUiEvent>()
    val selectRegisterUiEvent: LiveData<SelectRegisterUiEvent> = _selectRegisterUiEvent

    private val _storeRegisters = MutableLiveData<List<GetStoreRegistersData>>()
    val storeRegisters: LiveData<List<GetStoreRegistersData>> = _storeRegisters

    init {
        getStoreRegisters()
    }

    fun getStoreRegisters() {
        viewModelScope.launch {
            _selectRegisterUiEvent.value = SelectRegisterUiEvent.ShowLoading
            try {
                val response = storeRegistersRepository.getStoreRegisters(7)
                _selectRegisterUiEvent.value = SelectRegisterUiEvent.HideLoading

                if (response.getStoreRegistersDataList.isNotEmpty()) {
                    _storeRegisters.value = response.getStoreRegistersDataList

                } else
                    _storeRegisters.value = emptyList()

            } catch (e: Exception) {
                _selectRegisterUiEvent.value = SelectRegisterUiEvent.HideLoading
                _selectRegisterUiEvent.value =
                    SelectRegisterUiEvent.ShowError(e.message ?: "Something went wrong")
            }
        }
    }

    fun updateStoreRegister(storeID: Int, storeRegisterID: Int) {
        viewModelScope.launch {
            _selectRegisterUiEvent.value = SelectRegisterUiEvent.ShowLoading
            try {
                val response = storeRegistersRepository.updateStoreRegister(
                    UpdateStoreRegisterRequest(storeID, storeRegisterID)
                )
                response.message.let {
                    preferenceManager.setRegister(true)
                    _selectRegisterUiEvent.value = SelectRegisterUiEvent.NavigateToPinScreen
                }
                _selectRegisterUiEvent.value = SelectRegisterUiEvent.HideLoading

            } catch (e: Exception) {
                _selectRegisterUiEvent.value = SelectRegisterUiEvent.HideLoading
                _selectRegisterUiEvent.value =
                    SelectRegisterUiEvent.ShowError(e.message ?: "Something went wrong")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _selectRegisterUiEvent.value = SelectRegisterUiEvent.ShowLoading
            try {
                val response = storeRegistersRepository.logout()
                response.message.let {
                    preferenceManager.setLogin(false)
                    _selectRegisterUiEvent.value = SelectRegisterUiEvent.NavigateToLoginScreen
                }
                _selectRegisterUiEvent.value = SelectRegisterUiEvent.HideLoading

            } catch (e: Exception) {
                _selectRegisterUiEvent.value = SelectRegisterUiEvent.HideLoading
                _selectRegisterUiEvent.value =
                    SelectRegisterUiEvent.ShowError(e.message ?: "Something went wrong")
            }
        }
    }

    fun getUsername(): String {
        return preferenceManager.getUsername()
    }
}