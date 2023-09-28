package com.gk.logindemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RegisterUser::class], version = 1)

abstract class RegisterUserDatabase : RoomDatabase() {

    abstract val registerUserDAO: RegisterUserDAO

    companion object{
        @Volatile
        private var INSTANCE : RegisterUserDatabase? = null
        fun getInstance(context: Context) : RegisterUserDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RegisterUserDatabase::class.java,
                        "register_user_data_table"
                    ).build()
                    INSTANCE = instance
                }
                return instance;
            }
        }
    }
}