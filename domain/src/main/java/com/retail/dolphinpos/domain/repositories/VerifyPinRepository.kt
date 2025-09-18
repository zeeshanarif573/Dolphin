package com.retail.dolphinpos.domain.repositories

import com.retail.dolphinpos.domain.model.auth.users.User

interface VerifyPinRepository {

    suspend fun getCompleteUserData(pin: String): User?

}