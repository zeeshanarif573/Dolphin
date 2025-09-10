package com.retail.dolphinpos.presentation.features.ui.auth.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retail.dolphinpos.common.Headers
import com.retail.dolphinpos.common.PreferenceManager
import com.retail.dolphinpos.data.entities.UserEntity
import com.retail.dolphinpos.data.mapper.UserMapper
import com.retail.dolphinpos.data.room.DolphinDatabase
import com.retail.dolphinpos.domain.models.login.request.LoginRequest
import com.retail.dolphinpos.domain.models.login.response.LoginData
import com.retail.dolphinpos.domain.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: LoginRepository,
    private val preferenceManager: PreferenceManager
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
                    preferenceManager.setRegister(true)
                    Headers.accessToken = loginData.accessToken
                    Headers.refreshToken = loginData.refreshToken
                    insertLoginDataIntoLocalDB(loginData, password)
                    _loginUiEvent.value = LoginUiEvent.NavigateToRegister

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

    fun insertLoginDataIntoLocalDB(loginData: LoginData, password: String) {
        val userLoginDetailsEntity =
            UserMapper.loginDetailsToUserEntity(
                loginDetails = loginData,
                password = password
            )
        val database = DolphinDatabase.getDatabase(context = context)
        database.userLoginDetailsDao().insertUser(userLoginDetailsEntity)
    }

}