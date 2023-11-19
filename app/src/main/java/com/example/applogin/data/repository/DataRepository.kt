package com.example.applogin.data.repository

import android.content.Context
import android.provider.ContactsContract.Data
import com.example.applogin.data.db.loginData
import com.example.applogin.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepository(context: Context) {

    /**
     * Se crea una instancia de la base de datos (AppDatabase) utilizando el contexto proporcionado al UserRepository.
     * Esto asegura que el repositorio tenga acceso a la base de datos para realizar operaciones.
     */
    private val database = loginData.getInstance(context)

    suspend fun validateLogin(user: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val userFromDb = database.userDao().findByEmailAndPassword(user, password)
            userFromDb != null
        }
    }

    suspend fun getUsers(): List<Data> {
        return withContext(Dispatchers.IO) {
            database.userDao().getAll()
        }
    }
    suspend fun saveUser(
        user: String,
        password: String
    ) {

        /**
         * Se utiliza withContext(Dispatchers.IO) para cambiar al hilo de fondo, ya que las
         * operaciones de base de datos son operaciones que deben realizarse en un hilo de
         * fondo para evitar bloquear el hilo principal y mantener una interfaz de usuario receptiva.
         */
        withContext(Dispatchers.IO) {
            val user = UserModel(
                user = user,
                password = password
            )
            database.userDao().insert(user)
        }
    }
}