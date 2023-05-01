package com.team23.design_system.image

import androidx.annotation.DrawableRes

sealed class NeuracrImageProperty(open val contentDescription: String?) {
    object None : NeuracrImageProperty(null)

    data class Remote(
        override val contentDescription: String?,
        val url: String,
    ) : NeuracrImageProperty(contentDescription)

    data class Resource(
        override val contentDescription: String?,
        @DrawableRes val imageRes: Int,
    ) : NeuracrImageProperty(contentDescription)
}
