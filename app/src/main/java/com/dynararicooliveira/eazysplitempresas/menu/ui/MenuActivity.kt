package com.dynararicooliveira.eazysplitempresas.menu.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.dynararicooliveira.eazysplitempresas.R
import com.dynararicooliveira.eazysplitempresas.login.ui.LoginActivity
import com.dynararicooliveira.eazysplitempresas.model.OptionMenu
import com.dynararicooliveira.eazysplitempresas.menu.adapter.OptionAdapter
import com.dynararicooliveira.eazysplitempresas.stores.ui.StoresActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        loadData()
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun loadData() {
        var options: List<OptionMenu> = listOf(
            OptionMenu(0,"Adicionar lojas"),
            OptionMenu(1,"Adicionar cardápio"),
            OptionMenu(2,"Logout"))
        showOptions(options)
    }

    private fun showOptions(options: List<OptionMenu>) {
        rvOptions.adapter = OptionAdapter(this, options) {
            when (it.id) {
                0 -> {
                    startActivity(Intent(this, StoresActivity::class.java))
                    finish()
                }
                1 -> {
                    Toast.makeText(this, it.name, Toast.LENGTH_LONG).show()
                }
                2 -> {
                    logout()
                }

            }
        }
        rvOptions.layoutManager = LinearLayoutManager(this)
    }

}
