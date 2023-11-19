package com.example.applogin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.applogin.R
import com.example.applogin.ui.adaptar.UserAdapter
import com.example.applogin.ui.viewmodel.UserView

class homeUser : AppCompatActivity() {

    private val tag = "ProfileActivity"

    private lateinit var userViewModel: UserView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_user)

        recyclerView = findViewById(R.id.recyclerViewUsers)
        userViewModel = ViewModelProvider(this).get(UserView::class.java)

        userViewModel.userList.observe(this) { userList ->
            Log.d(tag, "onCreate: $userList")
            val adapter = UserAdapter(userList)
            recyclerView.adapter = adapter

        }

        // Llama a la función para obtener la lista de usuarios
        userViewModel.getUsers()

        val buttonLog = findViewById<Button>(R.id.buttonLog)

        buttonLog.setOnClickListener {
            val sharePreferences = getSharedPreferences("ra2", MODE_PRIVATE)
            val editor = sharePreferences.edit()
            editor.clear()
            editor.apply()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }


    }


}