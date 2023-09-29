package com.gk.logindemo.db

class UserRepository(private val dao: UserDAO) {
    val users = dao.getAllRegisterUsers()

    suspend fun insert(registerUser: User): Long {
        return dao.insertRegisterUser(registerUser)
    }

    suspend fun update(registerUser: User): Int {
        return dao.updateRegisterUser(registerUser)
    }

    suspend fun delete(registerUser: User): Int {
        return dao.deleteRegisterUser(registerUser)
    }

    suspend fun deleteAll() {
        return dao.deleteAll()
    }

    fun getUserFromUserName(userName: String): User? {
        return dao.getUserFromUserName(userName)
    }
}