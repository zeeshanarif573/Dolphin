package com.retail.dolphinpos.domain.models.auth.select_register

import com.google.gson.annotations.SerializedName

data class GetStoreRegistersResponse(
    @SerializedName("data")
    val getStoreRegistersDataList: List<GetStoreRegistersData>
)