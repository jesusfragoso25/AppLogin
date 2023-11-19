package com.example.applogin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.applogin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        validateLog()

        val user = findViewById<EditText>(R.id.user)
        val pass = findViewById<EditText>(R.id.password)
        val butLogin = findViewById<Button>(R.id.login)

        butLogin.setOnClickListener{
            val username = user.text.toString()
            val password = pass.text.toString()

            val sharePrefence = getSharedPreferences("ra2", MODE_PRIVATE)
            val editor = sharePrefence.edit()

            editor.putString("username",username)
            editor.putString("password", password)
            editor.apply()

            val i = Intent(this, homeUser::class.java)
            startActivity(i)


        }

    }

    private fun validateLog(){
        val sharedPreferences = getSharedPreferences("ra2", MODE_PRIVATE)
        val username = sharedPreferences.getString("user", "")
        val password = sharedPreferences.getString("password", "")

        if (username != "" && password !=""){
            val i = Intent (this, homeUser::class.java)
            startActivity(i)
        }
    }



}