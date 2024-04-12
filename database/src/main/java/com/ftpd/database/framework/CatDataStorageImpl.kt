package com.ftpd.database.framework

import com.ftpd.database.data_source.service.CatDataStorage
import com.ftpd.database.domain.RealmFavoriteCat
import io.realm.kotlin.TypedRealm
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import javax.inject.Inject

class CatDataStorageImpl @Inject constructor(
    private val databaseQueue: DatabaseQueue
) : CatDataStorage {

    override suspend fun insertOrUpdateFavoriteCats(
        realmList: RealmList<RealmFavoriteCat>,
        realm: TypedRealm?
    ): RealmList<RealmFavoriteCat>? = databaseQueue.writeQueue(realm) {

        realmList
    }

    override suspend fun readFavoriteCats(realm: TypedRealm?): RealmList<RealmFavoriteCat>? =
        databaseQueue.readQueue(realm) {
            copyFromRealm(where(RealmFavoriteCat::class).findAll()).toRealmList()
        }

}