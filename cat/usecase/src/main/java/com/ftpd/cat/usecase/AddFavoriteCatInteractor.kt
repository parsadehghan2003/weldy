package com.ftpd.cat.usecase

import com.ftpd.base.Cat
import com.ftpd.cat.data_source.CatRepository

open class AddFavoriteCatInteractor(private val catRepository: CatRepository)  {
    suspend fun run(params: Cat) {
        return catRepository.addFavoriteCat(params)
    }

}