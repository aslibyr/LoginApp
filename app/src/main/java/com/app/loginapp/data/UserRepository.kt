package com.app.loginapp.data

import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    // Login
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity? {
        return userDao.getUser(email, password)
    }

    suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }
}
