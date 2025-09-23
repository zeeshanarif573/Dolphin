package com.retail.dolphinpos.data.di

import com.retail.dolphinpos.data.dao.UserDao
import com.retail.dolphinpos.data.repositories.CashDenominationRepositoryImpl
import com.retail.dolphinpos.data.repositories.LoginRepositoryImpl
import com.retail.dolphinpos.data.repositories.StoreRegisterRepositoryImpl
import com.retail.dolphinpos.data.repositories.VerifyPinRepositoryImpl
import com.retail.dolphinpos.data.service.ApiService
import com.retail.dolphinpos.domain.repositories.auth.CashDenominationRepository
import com.retail.dolphinpos.domain.repositories.auth.LoginRepository
import com.retail.dolphinpos.domain.repositories.auth.StoreRegistersRepository
import com.retail.dolphinpos.domain.repositories.auth.VerifyPinRepository
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
    fun provideStoreRegisterRepository(
        api: ApiService, userDao: UserDao
    ): StoreRegistersRepository {
        return StoreRegisterRepositoryImpl(api, userDao)
    }

    @Provides
    @Singleton
    fun provideVerifyPinRepository(
        userDao: UserDao
    ): VerifyPinRepository {
        return VerifyPinRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideCashDenominationRepository(
        userDao: UserDao
    ): CashDenominationRepository {
        return CashDenominationRepositoryImpl(userDao)
    }

}