package com.example.applogin.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.applogin.data.model.UserModel
import com.example.applogin.data.repository.DataRepository
import kotlinx.coroutines.launch

class UserView (application: Application): AndroidViewModel(application){

    private val userRepository = DataRepository(application)

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    private val _userSaved = MutableLiveData<Boolean>()
    val userSaved: LiveData<Boolean>
        get() = _userSaved

    private val _userList = MutableLiveData<List<UserModel>>()
    val userList: LiveData<List<UserModel>>
        get() = _userList


    fun validateLogin(user: String, password: String) {
        viewModelScope.launch {
            val isValidLogin = DataRepository.validateLogin(user, password)
            _loginResult.value = isValidLogin
        }
    }

    fun saveUser(
        user: String,
        password: String
    ) {

        // Utiliza viewModelScope.launch para realizar operaciones en un hilo de fondo
        viewModelScope.launch {

            /**
             * Dentro de esta función, se utiliza viewModelScope.launch para lanzar una corrutina en un hilo de fondo.
             * Luego, se llama al método saveUser del UserRepository, que contiene la lógica para guardar el usuario en la base de datos.
             */
            DataRepository.saveUser(user, password)
            _userSaved.value = true

        }
    }

    fun getUsers(){
        viewModelScope.launch {
            val users = DataRepository.getUsers()
            _userList.value = users
        }
    }
}