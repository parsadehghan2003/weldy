package com.ftpd.cat.data_source

import com.ftpd.base.BaseDomain
import com.ftpd.base.Cat
import com.ftpd.base.DataState

interface CatLocalService {
    suspend fun insertFavoriteCat(cat:Cat)
    suspend fun readFavoriteCats() : DataState<BaseDomain>
}