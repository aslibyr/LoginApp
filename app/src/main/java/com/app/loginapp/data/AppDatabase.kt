package com.app.loginapp.data

import androidx.room.Database

@Database(entities = [User::class], version = 1)
abstract class AppDatabase {
    abstract fun userDao(): UserDao
}