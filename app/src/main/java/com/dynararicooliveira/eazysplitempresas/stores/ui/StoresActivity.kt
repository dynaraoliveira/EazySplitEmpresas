package com.dynararicooliveira.eazysplitempresas.stores.ui

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dynararicooliveira.eazysplitempresas.R
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.location.Address
import com.dynararicooliveira.eazysplitempresas.menu.ui.MenuActivity
import com.dynararicooliveira.eazysplitempresas.model.AddressDatabase
import com.dynararicooliveira.eazysplitempresas.stores.viewmodel.StoresViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_stores.*

class StoresActivity : AppCompatActivity() {

    lateinit var storesViewModel: StoresViewModel
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)

        mAuth = FirebaseAuth.getInstance()

        storesViewModel = ViewModelProviders.of(this)
            .get(StoresViewModel::class.java)

        btSearch.setOnClickListener {
            storesViewModel.search(etZipCode.text.toString())
        }

        etZipCode.setOnEditorActionListener { _, _, _ ->
            storesViewModel.search(etZipCode.text.toString())
            true
        }

        storesViewModel.address.observe(this, Observer {
            etAddress.setText(it?.addressDescription)
            etAddressNeighborhood.setText(it?.addressNeighborhood)
            etAddressComplement.setText(it?.addressComplement)
            etAddressCity.setText(it?.addressLocality)
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

        btInsert.setOnClickListener {
            saveInRealtimeDatabase()
        }
    }

    private fun saveInRealtimeDatabase() {
        val address = AddressDatabase (
            etZipCode.text.toString(),
            etAddress.text.toString(),
            etAddressComplement.text.toString(),
            etAddressNeighborhood.text.toString(),
            etAddressCity.text.toString(),
            etAddressState.text.toString()
        )

        FirebaseDatabase.getInstance().getReference("Company")
            .child(mAuth.currentUser!!.uid)
            .setValue(address)
            .addOnCompleteListener {
                if (it.isSuccessful()) {
                    startActivity(Intent(this, MenuActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }
}
