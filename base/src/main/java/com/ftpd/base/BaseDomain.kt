package com.ftpd.base

interface BaseDomain {
    val actionType: ActionType
}

enum class ActionType {
    ERROR,
    GET_CATS_RESPONSE,
    GET_CATS_REQUEST,
    GET_FAVORITE_CATS_RESPONSE,
    GET_FAVORITE_CATS_REQUEST
}
