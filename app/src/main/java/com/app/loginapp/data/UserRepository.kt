package com.app.loginapp.data

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        return userDao.insertUser(user)
    }

    suspend fun checkIfUserExists(email: String): Boolean {
        return userDao.getUserByEmail(email) != null
    }
}
