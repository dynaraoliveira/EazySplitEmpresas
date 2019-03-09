package com.dynararicooliveira.eazysplitempresas.stores.ui

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dynararicooliveira.eazysplitempresas.R
import android.arch.lifecycle.ViewModelProviders
import com.dynararicooliveira.eazysplitempresas.stores.viewmodel.StoresViewModel
import kotlinx.android.synthetic.main.activity_stores.*

class StoresActivity : AppCompatActivity() {

    lateinit var storesViewModel: StoresViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)

        storesViewModel = ViewModelProviders.of(this)
            .get(StoresViewModel::class.java)

        btSearch.setOnClickListener {
            storesViewModel.search(etZipCode.text.toString())
        }

        storesViewModel.address.observe(this, Observer {
            etAddress.setText(it?.addressDescription)
            etAddressNeighborhood.setText(it?.addressNeighborhood)
            etAddressComplement.setText(it?.addressComplement)
            etAddressState.setText(it?.addressState)
        })

        storesViewModel.messageError.observe(this, Observer {
            if (!it.equals("")) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        storesViewModel.isLoading.observe(this, Observer {
                isLoading -> if(isLoading == true) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
        })
    }
}
