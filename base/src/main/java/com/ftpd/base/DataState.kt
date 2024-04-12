package com.ftpd.base

import com.ftpd.base.error_handler.ErrorModel


sealed class DataState<T> {

    data class Error<T>(
        val errorObject: ErrorModel,
        var data: T? = null
    ) : DataState<T>()


    data class Data<T>(
        val data: T? = null
    ) : DataState<T>()

}
