package com.eazysplit.empresas.address.api

import com.eazysplit.empresas.model.Address
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService {
    @GET("ws/{cep}/json/")
    fun search(@Path("cep")zipCode: String): Call<Address>
}


