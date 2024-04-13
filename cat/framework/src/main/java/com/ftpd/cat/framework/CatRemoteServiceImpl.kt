package com.ftpd.cat.framework

import com.ftpd.base.BaseDomain
import com.ftpd.base.DataState
import com.ftpd.base.error_handler.dataStateRemoteErrorHandler
import com.ftpd.cat.data_source.CatRemoteService
import com.ftpd.cat.domain.GetCatsObject
import com.ftpd.weldy.gateway.CatApiService
import javax.inject.Inject

class CatRemoteServiceImpl @Inject constructor(val catApiService: CatApiService) : CatRemoteService {
    override suspend fun getCats(baseDomain: BaseDomain): DataState<BaseDomain> {
        return try {
            val getCatsObject = baseDomain as GetCatsObject.GetPostsObjectRequest
            val response = catApiService.getCatList(getCatsObject.limit,getCatsObject.page)
            if (response.isSuccessful)
                DataState.Data(response.body()?.let {
                    GetCatsObject.GetPostsObjectResponse(it)
                })
            else dataStateRemoteErrorHandler(response.code())
        } catch (exception: Exception) {
            dataStateRemoteErrorHandler(0)
        }

    }
}