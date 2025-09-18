package com.retail.dolphinpos.presentation.features.ui.auth.select_register

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retail.dolphinpos.common.PreferenceManager
import com.retail.dolphinpos.domain.model.auth.select_registers.reponse.GetStoreRegistersData
import com.retail.dolphinpos.domain.model.auth.select_registers.request.UpdateStoreRegisterRequest
import com.retail.dolphinpos.domain.model.auth.users.LogoUrl
import com.retail.dolphinpos.domain.model.auth.users.Store
import com.retail.dolphinpos.domain.model.auth.users.User
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

    val defaultStore = Store(
        allowCustomDiscount = false,
        createdAt = "2025-03-26T18:52:03.000Z",
        deletedAt = null,
        dualPricePercentage = "3.75",
        endTime = "05:00:00",
        id = 7,
        isAdvertisement = false,
        isMultipleDIscountsAllowed = false,
        location = "zxcv",
        multiCashier = false,
        name = "The Lingerie Store",
        policy = "NO REFUNDS OR EXCHANGES",
        startTime = "12:00:00",
        status = "active",
        taxValue = 10,
        timezone = "Central Time (CT)",
        updatedAt = "2025-09-18T14:07:15.000Z",
        wpId = 2089,
        zipCode = "60159",
        logoUrl = LogoUrl(
            fileURL = "https://uat-lingerie.gotmsolutions.com/files/lingerie/uploads/fb293b7f-2349-40a6-a7bf-1f7587889edc%20-%20lingarie_logo.png",
            originalName = "lingarie_logo.png"
        )
    )

    val defaultUser = User(
        alreadyClockedIn = false,
        createdAt = "2025-03-26T18:53:07.000Z",
        deletedAt = null,
        email = "schaumburg",
        password = "schaumburg",
        id = 17,
        managerId = 16,
        name = "Jen",
        permissions = null,
        phoneNo = "1234567890",
        pin = "1234",
        roleId = 1,
        roleTitle = "Manager",
        status = "active",
        storeId = 7,
        updatedAt = "2025-08-12T16:31:45.000Z",
        username = "jen",
        store = defaultStore
    )

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
                    preferenceManager.setStoreRegisterID(storeRegisterID)
                    preferenceManager.setRegister(true)

                    storeRegistersRepository.insertUsersDataIntoLocalDB(
                        defaultUser,
                        preferenceManager.getPassword(),
                        17,
                        preferenceManager.getStoreID()
                    )
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