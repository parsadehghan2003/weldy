package com.ftpd.cat.data_source

import com.ftpd.base.BaseDomain
import com.ftpd.base.DataState

interface CatRepository {
    suspend fun getCats(baseDomain: BaseDomain) : DataState<BaseDomain>
}