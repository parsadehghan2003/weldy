package com.ftpd.cat.framework

import com.ftpd.base.Cat
import com.ftpd.database.domain.RealmFavoriteCat
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList


fun createCatObjectWithRealmFavoriteCat(realmObject: RealmFavoriteCat): Cat {
    return Cat().apply {
        id = realmObject.id
        url = realmObject.image
        width = realmObject.width
        height = realmObject.height
    }
}

fun createRealmFavoriteCatWithCatObject(cat: Cat): RealmFavoriteCat {
    return RealmFavoriteCat().apply {
        id = cat.id
        image = cat.url
        width = cat.width
        height = cat.height
    }
}

fun createCatRealmListWithCatsObject(list: List<Cat>): RealmList<RealmFavoriteCat> {
    val realmPlaces = realmListOf<RealmFavoriteCat>()
    list.map {
        realmPlaces.add(
            createRealmFavoriteCatWithCatObject(it)
        )
    }
    return realmPlaces
}


fun createCatsObjectWithCatRealmList(list: RealmList<RealmFavoriteCat>): List<Cat> {
    val cats = mutableListOf<Cat>()
    list.map {
        cats.add(
            createCatObjectWithRealmFavoriteCat(it)
        )
    }
    return cats
}
