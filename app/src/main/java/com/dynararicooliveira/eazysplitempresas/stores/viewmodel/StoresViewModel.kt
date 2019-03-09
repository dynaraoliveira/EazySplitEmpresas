package com.dynararicooliveira.eazysplitempresas.stores.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dynararicooliveira.eazysplitempresas.model.Address
import com.dynararicooliveira.eazysplitempresas.address.repository.AddressRepository


class StoresViewModel: ViewModel() {

    val addressRepository = AddressRepository()

    val address = MutableLiveData<Address>()
    val messageError = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun search(zipCode: String) {
        isLoading.value = true
        addressRepository.search(
            zipCode,
            onComplete = {
                address.value = it
                messageError.value = ""
                isLoading.value = false
            },
            onError = {
                address.value = null
                messageError.value = it?.message
                isLoading.value = false
            }
        )
    }
}