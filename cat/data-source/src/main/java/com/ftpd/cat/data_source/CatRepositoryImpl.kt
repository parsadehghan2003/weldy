package com.ftpd.cat.data_source

import com.ftpd.base.BaseDomain
import com.ftpd.base.DataState

class CatRepositoryImpl(private val catRemoteService: CatRemoteService) : CatRepository {
    override suspend fun getCats(baseDomain: BaseDomain): DataState<BaseDomain> {
        return catRemoteService.getCats(baseDomain)
    }
}