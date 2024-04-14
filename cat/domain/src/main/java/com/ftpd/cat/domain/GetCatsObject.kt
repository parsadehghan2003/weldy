package com.ftpd.cat.domain

import com.ftpd.base.ActionType
import com.ftpd.base.BaseDomain
import com.ftpd.base.Cat

sealed class GetCatsObject : BaseDomain {

    open class GetPostsObjectRequest(
        val limit:Int,
        val page:Int
    ) : GetCatsObject() {
        override val actionType: ActionType
            get() = ActionType.GET_CATS_REQUEST
    }

    open class GetPostsObjectResponse(
        val cats: List<Cat>,
    ) : GetCatsObject() {
        override val actionType: ActionType
            get() = ActionType.GET_CATS_RESPONSE

    }
}
