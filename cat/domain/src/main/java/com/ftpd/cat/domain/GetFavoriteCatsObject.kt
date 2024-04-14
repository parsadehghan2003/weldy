package com.ftpd.cat.domain

import com.ftpd.base.ActionType
import com.ftpd.base.BaseDomain
import com.ftpd.base.Cat

sealed class GetFavoriteCatsObject : BaseDomain {

    open class GetFavoriteCatsObjectResponse(
        val cats: List<Cat>,
    ) : GetFavoriteCatsObject() {
        override val actionType: ActionType
            get() = ActionType.GET_FAVORITE_CATS_RESPONSE

    }
}
