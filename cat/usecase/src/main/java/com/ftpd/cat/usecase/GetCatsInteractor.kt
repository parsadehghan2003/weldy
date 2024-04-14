package com.ftpd.cat.usecase

import com.ftpd.base.BaseDomain
import com.ftpd.base.DataState
import com.ftpd.base.interactor.UseCase
import com.ftpd.cat.data_source.CatRepository

open class GetCatsInteractor(private val catRepository: CatRepository) : UseCase<BaseDomain>() {
    override suspend fun run(params: BaseDomain?): DataState<BaseDomain> {
        return catRepository.getCats(params!!)
    }

}