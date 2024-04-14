package com.ftpd.database.data_source.service

import com.ftpd.database.domain.RealmFavoriteCat
import io.realm.kotlin.TypedRealm
import io.realm.kotlin.types.RealmList

interface CatDataStorage {
    suspend fun insertOrUpdateFavoriteCats(
        realmList: RealmList<RealmFavoriteCat>,
        realm: TypedRealm? = null
    ): RealmList<RealmFavoriteCat>?

    suspend fun readFavoriteCats(realm: TypedRealm? = null): RealmList<RealmFavoriteCat>?
}