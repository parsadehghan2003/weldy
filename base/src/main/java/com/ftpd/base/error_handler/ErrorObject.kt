package com.ftpd.base.error_handler

import com.ftpd.base.ActionType
import com.ftpd.base.BaseDomain

data class ErrorObject(
    val type: ErrorType = ErrorType.NOT_DEFINED,
    val message: String? = null
) : BaseDomain {
    override val actionType: ActionType
        get() = ActionType.ERROR
}