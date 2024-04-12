package com.ftpd.database.domain

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


open class RealmFavoriteCat : RealmObject {

    @PrimaryKey
    var id:String = ""
    var image: String = ""
    var width: Int = 0
    var height: Int = 0

}