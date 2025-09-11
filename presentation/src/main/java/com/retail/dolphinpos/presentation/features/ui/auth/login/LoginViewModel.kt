package com.retail.dolphinpos.presentation.features.ui.auth.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retail.dolphinpos.common.Headers
import com.retail.dolphinpos.domain.models.login.request.LoginRequest
import com.retail.dolphinpos.domain.models.login.response.User
import com.retail.dolphinpos.domain.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: LoginRepository
) : ViewModel() {

    private val _loginUiEvent = MutableLiveData<LoginUiEvent>()
    val loginUiEvent: LiveData<LoginUiEvent> = _loginUiEvent

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginUiEvent.value = LoginUiEvent.ShowLoading
            try {
                val response = repository.login(LoginRequest(username, password))
                _loginUiEvent.value = LoginUiEvent.HideLoading

                response.loginData?.let { loginData ->
                    Headers.accessToken = loginData.accessToken
                    Headers.refreshToken = loginData.refreshToken
                    insertDataIntoLocalDB(loginData.user, password)

//                    _loginUiEvent.value = LoginUiEvent.NavigateToRegister

                } ?: run {
                    _loginUiEvent.value =
                        LoginUiEvent.ShowError(response.message ?: "No data received")
                }
            } catch (e: Exception) {
                _loginUiEvent.value = LoginUiEvent.HideLoading
                _loginUiEvent.value = LoginUiEvent.ShowError(e.message ?: "Something went wrong")
            }
        }
    }

    suspend fun insertDataIntoLocalDB(user: User, password: String) {
        val inserted = repository.insertUserDataIntoLocalDB(user, password)
        if (inserted) {
            Log.e("Local DB", "Inserted")
            getUserFromLocalDB()
        } else
            Log.e("Local DB", "Not Inserted")
    }

    fun getUserFromLocalDB() {
        viewModelScope.launch {
            val user = repository.getUser()
            Log.e("User", user.toString())
        }
    }
}