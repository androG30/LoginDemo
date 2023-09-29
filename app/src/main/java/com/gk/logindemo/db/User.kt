package com.gk.logindemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register_user_data_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "register_id")
    val id: Int,

    @ColumnInfo(name = "register_username")
    var username: String,

    @ColumnInfo(name = "register_email")
    var email: String,

    @ColumnInfo(name = "register_course")
    var course: String,

    @ColumnInfo(name = "register_password")
    var password: String,
)