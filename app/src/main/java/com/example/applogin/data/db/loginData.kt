package com.example.applogin.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.applogin.data.model.UserModel


@Database(entities = [UserModel::class], version = 2)
abstract class loginData {

    abstract fun userDao(): UserDao


    companion object {
        @Volatile
        private var INSTANCE: loginData? = null;
        fun getInstance(context: Context): loginData {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, loginData::class.java,
                    name = "ra_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}