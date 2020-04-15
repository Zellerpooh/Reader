package com.zeller.reader.dagger.inoreader

import com.zeller.reader.login.data.LoginLocalDataSource
import com.zeller.reader.login.data.LoginRemoteDataSource
import com.zeller.reader.login.data.LoginRepository
import dagger.Module
import dagger.Provides


@Module
class InoReaderModule {

    @Provides
    fun provideLoginRepository(
        localSource: LoginLocalDataSource,
        remoteSource: LoginRemoteDataSource
    ): LoginRepository =
        LoginRepository.getInstance(localSource, remoteSource)



}