package com.ftpd.base.error_handler


interface IError {
    fun createError() : ErrorModel
}