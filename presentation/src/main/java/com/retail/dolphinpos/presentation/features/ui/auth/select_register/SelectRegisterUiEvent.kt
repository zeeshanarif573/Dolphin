package com.retail.dolphinpos.presentation.features.ui.auth.select_register

import com.retail.dolphinpos.domain.model.auth.login.response.Locations

sealed class SelectRegisterUiEvent {
    object ShowLoading : SelectRegisterUiEvent()
    object HideLoading : SelectRegisterUiEvent()
    data class ShowError(val message: String) : SelectRegisterUiEvent()

    data class PopulateLocationsList(val locationsList: List<Locations>) : SelectRegisterUiEvent()
    object NavigateToPinScreen : SelectRegisterUiEvent()
    object NavigateToLoginScreen : SelectRegisterUiEvent()
}