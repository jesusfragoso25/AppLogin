package com.example.applogin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.applogin.R
import com.example.applogin.ui.viewmodel.UserView
import com.example.applogin.util.Common

class registerUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)


        val name = findViewById<EditText>(R.id.nameRegister)
        val pass = findViewById<EditText>(R.id.passRegister)
        val user = findViewById<EditText>(R.id.userRegister)
        val buttonR = findViewById<Button>(R.id.register)

        buttonR.setOnClickListener() {
            val email = user.text.toString()
            val contrasena = pass.text.toString()
            if (isValidInput(email, contrasena)) {
                guardar(email, contrasena);
                showToast("Registro exitoso")
                guardarDb(email, contrasena)
                irHome()
            }
        }
    }
    private fun isValidInput(
        email: String,
        password: String
    ): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            showToast(getString(R.string.txt_validar_datos))
            return false
        }
        return true
    }

    private fun showToast(message: String) {
        Common.showToast(this, message)
    }

    private fun guardar(user: String, password: String) {
        val sharedPreferences = getSharedPreferences("ra2", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("user", user)
        editor.putString("password", password)
        editor.apply()
    }

    private fun irHome() {
        val intent = Intent(this, homeUser::class.java)
        startActivity(intent)
    }

    private fun guardarDb(
        user: String,
        password: String
    ) {
        UserView.saveUser(user, password)
    }

}