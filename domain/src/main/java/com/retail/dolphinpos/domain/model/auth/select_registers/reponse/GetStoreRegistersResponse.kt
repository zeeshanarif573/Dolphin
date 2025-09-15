package com.retail.dolphinpos.domain.model.auth.select_registers.reponse

import com.google.gson.annotations.SerializedName

data class GetStoreRegistersResponse(
    @SerializedName("data")
    val getStoreRegistersDataList: List<GetStoreRegistersData>
)