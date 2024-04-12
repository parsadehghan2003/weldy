package com.ftpd.base.error_handler

class HttpErrorHandler(private val errorCode: Int) : ErrorHandler() {
    override fun createErrorStatus(): IError {
        return HttpServerError(errorCode)
    }
}