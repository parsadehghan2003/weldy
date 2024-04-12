package com.ftpd.database.di

import com.ftpd.database.data_source.service.CatDataStorage
import com.ftpd.database.domain.RealmFavoriteCat
import com.ftpd.database.framework.DatabaseQueue
import com.ftpd.database.framework.CatDataStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.plus
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabaseScope(): CoroutineScope = GlobalScope + Dispatchers.IO

    @Provides
    @Singleton
    fun provideDatabaseCoroutineQueue(): ExecutorCoroutineDispatcher =
        Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @Provides
    @Singleton
    fun provideRealmConfig(): RealmConfiguration {
        return RealmConfiguration.Builder(setOf(RealmFavoriteCat::class))
            .name("Weldy")
            .schemaVersion(0)
            .build()
    }

    @Provides
    @Singleton
    fun provideCatDataStorage(
        databaseQueue: DatabaseQueue
    ): CatDataStorage =
        CatDataStorageImpl(databaseQueue)

    @Provides
    @Singleton
    fun provideDatabaseQueue(
        databaseScope: CoroutineScope,
        databaseQueue: ExecutorCoroutineDispatcher,
        realmConfiguration: RealmConfiguration,
    ): DatabaseQueue = DatabaseQueue(databaseScope, databaseQueue, realmConfiguration)


}