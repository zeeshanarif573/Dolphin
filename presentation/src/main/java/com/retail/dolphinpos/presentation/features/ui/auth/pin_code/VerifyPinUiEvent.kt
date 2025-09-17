package com.retail.dolphinpos.presentation.features.ui.auth.pin_code

sealed class VerifyPinUiEvent {
    object ShowLoading : VerifyPinUiEvent()
    object HideLoading : VerifyPinUiEvent()
    data class ShowError(val message: String) : VerifyPinUiEvent()
    object NavigateToCashDenomination : VerifyPinUiEvent()
}