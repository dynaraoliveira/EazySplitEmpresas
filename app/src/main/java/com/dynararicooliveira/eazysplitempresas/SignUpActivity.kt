package com.dynararicooliveira.eazysplitempresas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dynararicooliveira.eazysplitempresas.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            etPassword.setText("123456")
            etPassword.isEnabled = false
            etPasswordConfirmation.setText("123456")
            etPasswordConfirmation.isEnabled = false
            mAuth.currentUser?.displayName.let { etName.setText(it) }
            mAuth.currentUser?.email.let { etEmail.setText(it) }
        }

        btCreate.setOnClickListener {
            if (validateFields()) {
                if (mAuth.currentUser == null) {
                    mAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString())
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                saveInRealtimeDatabase()
                            } else {
                                val text = it.exception?.message
                                Toast.makeText(this, text, Toast.LENGTH_LONG).show()
                            }
                        }
                }else {
                    saveInRealtimeDatabase()
                }
            }
        }
    }

    private fun validateFields() : Boolean {
        var message = ""

        if (etPassword.text.toString() != etPasswordConfirmation.text.toString()) message = "Senha não confere"
        if (etPassword.text.toString() == "" || etPasswordConfirmation.text.toString() == "") message = "Preencha a senha"
        if (etEmail.text.toString() == "") message = "Preencha o e-mail"
        if (etName.text.toString() == "") message = "Preencha o nome"

        if (message != "") {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    private fun saveInRealtimeDatabase() {
        val user = User(
            etName.text.toString(),
            etEmail.text.toString(),
            etPhone.text.toString()
        )

        FirebaseDatabase.getInstance().getReference("User")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(user)
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
