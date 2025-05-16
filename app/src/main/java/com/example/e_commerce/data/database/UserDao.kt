package com.example.e_commerce.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.e_commerce.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users WHERE email = :email AND passwordHash = :passwordHash LIMIT 1")
    suspend fun getUserByEmailAndPassword(email: String, passwordHash: String): User?
}

