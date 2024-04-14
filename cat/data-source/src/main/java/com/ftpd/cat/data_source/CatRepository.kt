package com.ftpd.cat.data_source

import com.ftpd.base.BaseDomain
import com.ftpd.base.Cat
import com.ftpd.base.DataState

interface CatRepository {
    suspend fun getCats(baseDomain: BaseDomain) : DataState<BaseDomain>
    suspend fun getFavoriteCats() : DataState<BaseDomain>
    suspend fun addFavoriteCat(cat:Cat)
}