package io.softpay.softpos.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.softpay.sdk.TransactionManager
import io.softpay.sdk.impl.TransactionManagerImpl
import io.softpay.softpos.data.remote.StringProvider
import io.softpay.softpos.data.remote.StringProviderImpl
import io.softpay.softpos.data.repository.RepositoryImp
import io.softpay.softpos.domain.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideStringProvider(@ApplicationContext appContext: Context): StringProvider {
        return StringProviderImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideGameRepository(repositoryImp: RepositoryImp): Repository = RepositoryImp()


    @Provides
    @Singleton
    fun provideTransactionManager(@ApplicationContext mContext: Context): TransactionManager =
        TransactionManagerImpl()

}