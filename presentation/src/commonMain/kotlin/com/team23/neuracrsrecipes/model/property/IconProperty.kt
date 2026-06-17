package com.team23.neuracrsrecipes.model.property

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Immutable
sealed interface IconProperty {
    val contentDescription: StringResource?
    val tint: ColorProperty

    data class Resource(
        val drawableResource: DrawableResource,
        override val contentDescription: StringResource? = null,
        override val tint: ColorProperty = ColorProperty.Unspecified,
    ) : IconProperty

    data class Vector(
        val imageVector: ImageVector,
        override val contentDescription: StringResource? = null,
        override val tint: ColorProperty = ColorProperty.Unspecified,
    ) : IconProperty
}
