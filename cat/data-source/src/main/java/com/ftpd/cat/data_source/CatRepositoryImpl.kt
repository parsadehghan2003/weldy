package com.ftpd.cat.data_source

import com.ftpd.base.BaseDomain
import com.ftpd.base.Cat
import com.ftpd.base.DataState

class CatRepositoryImpl(private val catRemoteService: CatRemoteService, val catLocalService: CatLocalService) : CatRepository {
    override suspend fun getCats(baseDomain: BaseDomain): DataState<BaseDomain> {
        return catRemoteService.getCats(baseDomain)
    }

    override suspend fun getFavoriteCats(): DataState<BaseDomain> {
        return catLocalService.readFavoriteCats()
    }

    override suspend fun addFavoriteCat(cat: Cat) {
        catLocalService.insertFavoriteCat(cat)
    }
}