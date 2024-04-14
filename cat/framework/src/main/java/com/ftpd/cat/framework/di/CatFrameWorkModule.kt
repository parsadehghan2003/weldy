package com.ftpd.cat.framework.di

import com.ftpd.cat.data_source.CatRemoteService
import com.ftpd.cat.framework.CatRemoteServiceImpl
import com.ftpd.weldy.gateway.CatApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object CatFrameWorkModule {

    @Provides
    @ViewModelScoped
    fun provideCateRemoteService(catApiService: CatApiService): CatRemoteService {
        return CatRemoteServiceImpl(catApiService)
    }

}