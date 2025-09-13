package com.retail.dolphinpos.data.di

import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.data.repositories.LoginRepositoryImpl
import com.retail.dolphinpos.data.repositories.StoreRegisterRepositoryImpl
import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.repositories.LoginRepository
import com.retail.dolphinpos.domain.repositories.StoreRegistersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(api: ApiService, userDao: UserDao): LoginRepository {
        return LoginRepositoryImpl(api, userDao)
    }

    @Provides
    @Singleton
    fun provideStoreRegisterRepository(api: ApiService): StoreRegistersRepository {
        return StoreRegisterRepositoryImpl(api)
    }

}