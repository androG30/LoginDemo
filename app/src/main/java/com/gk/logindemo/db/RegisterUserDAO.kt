package com.gk.logindemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RegisterUserDAO {
    @Insert
    suspend fun insertRegisterUser(registerUser: RegisterUser): Long

    @Update
    suspend fun updateRegisterUser(registerUser: RegisterUser): Int

    @Delete
    suspend fun deleteRegisterUser(registerUser: RegisterUser): Int

    @Query("DELETE FROM register_user_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM REGISTER_USER_DATA_TABLE")
    fun getAllRegisterUsers(): LiveData<List<RegisterUser>>

}