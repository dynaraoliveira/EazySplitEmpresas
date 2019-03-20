package com.eazysplit.empresas.model

import com.google.gson.annotations.SerializedName

data class Address (
    @SerializedName("cep") val addressZipCode: String,
    @SerializedName("logradouro") val addressDescription: String,
    @SerializedName("complemento") val addressComplement: String,
    @SerializedName("bairro") val addressNeighborhood: String,
    @SerializedName("localidade") val addressLocality: String,
    @SerializedName("uf") val addressState: String
)

data class AddressDatabase (
    val addressZipCode: String = "",
    val addressDescription: String = "",
    val addressComplement: String = "",
    val addressNeighborhood: String = "",
    val addressCity: String = "",
    val addressState: String = ""
)