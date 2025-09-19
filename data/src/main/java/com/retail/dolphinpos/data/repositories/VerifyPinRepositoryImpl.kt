package com.retail.dolphinpos.data.repositories

import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.domain.repositories.VerifyPinRepository

class VerifyPinRepositoryImpl(
   private val userDao: UserDao
) : VerifyPinRepository {

}