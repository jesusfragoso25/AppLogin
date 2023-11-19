package com.example.applogin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Data")
data class UserModel(
    @PrimaryKey(autoGenerate = true) val uid: Long?=null,
    @ColumnInfo(name = "user") val user: String?,
    @ColumnInfo(name = "password") val password: String?
)
