package com.ftpd.cat.usecase

import com.ftpd.base.BaseDomain
import com.ftpd.base.DataState
import com.ftpd.base.interactor.UseCase
import com.ftpd.cat.data_source.CatRepository

open class GetFavoriteCatsInteractor(private val catRepository: CatRepository) : UseCase<Void?>() {
    override suspend fun run(params: Void?): DataState<BaseDomain> {
        return catRepository.getFavoriteCats()
    }

}