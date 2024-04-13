package com.ftpd.cat.data_source

import com.ftpd.base.BaseDomain
import com.ftpd.base.DataState

interface CatRemoteService {
    suspend fun getCats(baseDomain: BaseDomain) : DataState<BaseDomain>
}