package com.drax.sendit.data.repo

import com.drax.sendit.data.model.User
import com.drax.sendit.domain.repo.RegistryRepository
import com.drax.sendit.domain.repo.UserRepository
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val registryRepository: RegistryRepository
) : UserRepository {
//    override fun getUserSync() = registryRepository.getUser()
    override fun getUser() = registryRepository.getUser()

    override suspend fun addOrUpdateUser(user: User) = registryRepository.updateUser(user)
    override suspend fun clearDb() = registryRepository.updateUser(null)
}