package com.htetarkarzaw.data.remote.dto.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompanyDto(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("logo_path")
    val logoPath: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("origin_country")
    val originCountry: String = ""
)