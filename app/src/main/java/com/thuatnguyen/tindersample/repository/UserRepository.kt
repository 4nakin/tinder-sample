package com.thuatnguyen.tindersample.repository

import com.thuatnguyen.tindersample.api.UserApiService
import com.thuatnguyen.tindersample.db.UserDao
import com.thuatnguyen.tindersample.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApiService: UserApiService,
    private val userDao: UserDao
) {

    fun getUsersFromNetwork(): Flow<List<User>> {
        return flow {
            val user = userApiService.getUsers().results.map { it.user }
            emit(user)
        }.flowOn(Dispatchers.IO)
    }

    fun getFavoriteUsers(): Flow<List<User>> {
        return userDao.getAll()
    }

    fun saveFavoriteUser(user: User) {
        userDao.insert(user)
    }
}