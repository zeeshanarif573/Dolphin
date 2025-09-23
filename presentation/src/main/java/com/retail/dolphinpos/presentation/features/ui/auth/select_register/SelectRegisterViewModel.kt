package com.retail.dolphinpos.presentation.features.ui.auth.select_register

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retail.dolphinpos.common.PreferenceManager
import com.retail.dolphinpos.domain.model.auth.select_registers.request.UpdateStoreRegisterRequest
import com.retail.dolphinpos.domain.repositories.auth.StoreRegistersRepository
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


    init {
        getStoreLocations()
    }

    fun getStoreLocations() {
        viewModelScope.launch {
            _selectRegisterUiEvent.value = SelectRegisterUiEvent.ShowLoading
            try {
                val response = storeRegistersRepository.getLocations(preferenceManager.getStoreID())
                _selectRegisterUiEvent.value = SelectRegisterUiEvent.HideLoading
                if (response.isNotEmpty()) {
                    _selectRegisterUiEvent.value =
                        SelectRegisterUiEvent.PopulateLocationsList(response)
                } else
                    SelectRegisterUiEvent.ShowError("No Location Found")

            } catch (e: Exception) {
                _selectRegisterUiEvent.value = SelectRegisterUiEvent.HideLoading
                _selectRegisterUiEvent.value =
                    SelectRegisterUiEvent.ShowError(e.message ?: "Something went wrong")
            }
        }
    }

    fun getStoreRegisters(locationID: Int) {
        viewModelScope.launch {
            _selectRegisterUiEvent.value = SelectRegisterUiEvent.ShowLoading
            try {
                val response =
                    storeRegistersRepository.getRegistersByLocationID(locationID)
                _selectRegisterUiEvent.value = SelectRegisterUiEvent.HideLoading
                if (response.isNotEmpty()) {
                    _selectRegisterUiEvent.value =
                        SelectRegisterUiEvent.PopulateRegistersList(response)
                } else
                    SelectRegisterUiEvent.ShowError("No Registers Found")

            } catch (e: Exception) {
                _selectRegisterUiEvent.value = SelectRegisterUiEvent.HideLoading
                _selectRegisterUiEvent.value =
                    SelectRegisterUiEvent.ShowError(e.message ?: "Something went wrong")
            }
        }
    }

    fun updateStoreRegister(locationID: Int, storeRegisterID: Int) {
        viewModelScope.launch {
            _selectRegisterUiEvent.value = SelectRegisterUiEvent.ShowLoading
            try {
                val response = storeRegistersRepository.updateStoreRegister(
                    UpdateStoreRegisterRequest(
                        preferenceManager.getStoreID(),
                        locationID,
                        storeRegisterID
                    )
                )
                response.message.let {
                    preferenceManager.setRegister(true)
                    preferenceManager.setOccupiedLocationID(locationID)
                    preferenceManager.setOccupiedRegisterID(storeRegisterID)
                    storeRegistersRepository.insertRegisterStatusDetailsIntoLocalDB(response.data)
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

    fun getName(): String {
        return preferenceManager.getName()
    }
}