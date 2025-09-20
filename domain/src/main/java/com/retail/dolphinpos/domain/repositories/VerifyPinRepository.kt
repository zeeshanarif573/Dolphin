package com.retail.dolphinpos.domain.repositories

import com.retail.dolphinpos.domain.model.active_user.ActiveUserDetails
import com.retail.dolphinpos.domain.model.auth.login.response.AllStoreUsers
import com.retail.dolphinpos.domain.model.auth.login.response.Locations
import com.retail.dolphinpos.domain.model.auth.login.response.Registers

interface VerifyPinRepository {

    suspend fun getUser(pin: String): AllStoreUsers?

    suspend fun getLocationByLocationID(locationID: Int): Locations

    suspend fun getRegisterByRegisterID(locationID: Int): Registers

    suspend fun insertActiveUserDetailsIntoLocalDB(activeUserDetails: ActiveUserDetails)

}