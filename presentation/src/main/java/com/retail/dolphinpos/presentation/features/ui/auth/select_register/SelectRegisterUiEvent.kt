package com.retail.dolphinpos.presentation.features.ui.auth.select_register

sealed class SelectRegisterUiEvent {
    object ShowLoading : SelectRegisterUiEvent()
    object HideLoading : SelectRegisterUiEvent()
    data class ShowError(val message: String) : SelectRegisterUiEvent()
    object NavigateToPinScreen : SelectRegisterUiEvent()
}