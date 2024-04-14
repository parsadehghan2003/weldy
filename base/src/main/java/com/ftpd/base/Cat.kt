package com.ftpd.base

import java.io.Serializable

data class Cat(
    var id: String = "",
    var url: String = "",
    var width: Int = 0,
    var height: Int = 0
) : Serializable
