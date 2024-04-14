package com.ftpd.weldy.gateway

import com.ftpd.base.Cat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface CatApiService {
    @GET("images/search")
    suspend fun getCatList(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<List<Cat>>

}