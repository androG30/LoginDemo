package com.gk.logindemo.db

class RegisterUserRepository(private val dao: RegisterUserDAO) {
    val users = dao.getAllRegisterUsers()

    suspend fun insert(registerUser: RegisterUser): Long {
        return dao.insertRegisterUser(registerUser)
    }

    suspend fun update(registerUser: RegisterUser): Int {
        return dao.updateRegisterUser(registerUser)
    }

    suspend fun delete(registerUser: RegisterUser): Int {
        return dao.deleteRegisterUser(registerUser)
    }

    suspend fun deleteAll() {
        return dao.deleteAll()
    }
}