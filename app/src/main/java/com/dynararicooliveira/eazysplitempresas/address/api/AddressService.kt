package com.dynararicooliveira.eazysplitempresas.address.api

import com.dynararicooliveira.eazysplitempresas.model.Address
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService {
    @GET("ws/{cep}/json/")
    fun search(@Path("cep")zipCode: String): Call<Address>
}


