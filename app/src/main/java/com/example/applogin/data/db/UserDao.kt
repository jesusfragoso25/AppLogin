package com.example.applogin.data.db

import android.provider.ContactsContract.Data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {
    @Query("SELECT * FROM Data")
    fun getAll(): List<Data>

    @Query("SELECT * FROM Data WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Data>

    @Query("SELECT * FROM Data WHERE user LIKE :first AND " +
            "password LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Data

    @Query("SELECT * FROM Data WHERE user = :email AND password = :password LIMIT 1")
    fun findByEmailAndPassword(email: String, password: String);

    @Insert
    fun insert(vararg users: Data)

    @Delete
    fun delete(user: Data)
}