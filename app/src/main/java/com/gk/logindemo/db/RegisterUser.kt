package com.gk.logindemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register_user_data_table")
data class RegisterUser(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "register_id")
    val id: Int,

    @ColumnInfo(name = "register_username")
    val username: String,

    @ColumnInfo(name = "register_email")
    val email: String,

    @ColumnInfo(name = "register_course")
    val course: String,

    @ColumnInfo(name = "register_password")
    val password: String,
)