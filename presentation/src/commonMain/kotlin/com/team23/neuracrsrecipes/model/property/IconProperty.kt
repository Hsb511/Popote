package com.team23.neuracrsrecipes.model.property

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface IconProperty {
    val contentDescription: String?
    val tint: Color

    data class Resource(
        val fileName: String,
        override val contentDescription: String? = null,
        override val tint: Color = Color.Unspecified,
    ) : IconProperty

    data class Vector(
        val imageVector: ImageVector,
        override val contentDescription: String? = null,
        override val tint: Color = Color.Unspecified,
    ) : IconProperty
}
