package com.ftpd.cat.framework

import com.ftpd.base.BaseDomain
import com.ftpd.base.Cat
import com.ftpd.base.DataState
import com.ftpd.cat.data_source.CatLocalService
import com.ftpd.cat.domain.GetFavoriteCatsObject
import com.ftpd.database.data_source.service.CatDataStorage
import io.realm.kotlin.ext.realmListOf
import javax.inject.Inject

class CatLocalServiceImpl @Inject constructor(val catDataStorage: CatDataStorage) :
    CatLocalService {
    override suspend fun insertFavoriteCat(cat: Cat) {
        catDataStorage.insertOrUpdateFavoriteCats(createCatRealmListWithCatsObject(listOf(cat)))
    }

    override suspend fun readFavoriteCats(): DataState<BaseDomain> {
        return DataState.Data(
            GetFavoriteCatsObject.GetFavoriteCatsObjectResponse(
                createCatsObjectWithCatRealmList(catDataStorage.readFavoriteCats() ?: realmListOf())
            )
        )
    }
}