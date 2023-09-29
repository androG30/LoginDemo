package com.gk.logindemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {
    @Insert
    suspend fun insertRegisterUser(registerUser: User): Long

    @Update
    suspend fun updateRegisterUser(registerUser: User): Int

    @Delete
    suspend fun deleteRegisterUser(registerUser: User): Int

    @Query("DELETE FROM register_user_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM REGISTER_USER_DATA_TABLE")
    fun getAllRegisterUsers(): LiveData<List<User>>

    @Query("SELECT * FROM REGISTER_USER_DATA_TABLE WHERE register_username = :userName")
    fun getUserFromUserName(userName: String): User?
}