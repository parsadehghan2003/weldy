package com.ftpd.cat.ui.di

import com.ftpd.cat.data_source.CatLocalService
import com.ftpd.cat.data_source.CatRemoteService
import com.ftpd.cat.data_source.CatRepository
import com.ftpd.cat.data_source.CatRepositoryImpl
import com.ftpd.cat.usecase.AddFavoriteCatInteractor
import com.ftpd.cat.usecase.GetCatsInteractor
import com.ftpd.cat.usecase.GetFavoriteCatsInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object CatModule {
    @Provides
    @ViewModelScoped
    fun provideCatRepository(
        catRemoteService:CatRemoteService,
        catLocalService: CatLocalService
    ): CatRepository {
        return CatRepositoryImpl(catRemoteService,catLocalService)
    }
    @Provides
    @ViewModelScoped
    fun provideGetCatsInteractor(
        catRepository: CatRepository
    ): GetCatsInteractor {
        return GetCatsInteractor(catRepository)
    }
    @Provides
    @ViewModelScoped
    fun provideAddFavoriteCatInteractor(
        catRepository: CatRepository
    ): AddFavoriteCatInteractor {
        return AddFavoriteCatInteractor(catRepository)
    }
    @Provides
    @ViewModelScoped
    fun provideGetFavoriteCatsInteractor(
        catRepository: CatRepository
    ): GetFavoriteCatsInteractor {
        return GetFavoriteCatsInteractor(catRepository)
    }
}