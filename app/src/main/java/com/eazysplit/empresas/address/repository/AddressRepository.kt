package com.eazysplit.empresas.address.repository

import com.eazysplit.empresas.api.getAddressService
import com.eazysplit.empresas.model.Address
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddressRepository {
    fun search(zipCode: String, onComplete:(Address?) -> Unit, onError:(Throwable?) -> Unit) {
        getAddressService()
            .search(zipCode)
            .enqueue(object: Callback<Address> {
                override fun onFailure(call: Call<Address>?, t: Throwable?) {
                    onError(t)
                }

                override fun onResponse(call: Call<Address>?, response: Response<Address>?) {
                    if(response?.isSuccessful == true) {
                        onComplete(response?.body())
                    } else {
                        onError(Throwable(response?.errorBody()?.string()))
                    }

                }

            })
    }
}