package com.team23.view.sample.property

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.team23.neuracrsrecipes.model.property.ColorProperty
import com.team23.neuracrsrecipes.model.property.IconProperty

internal class ButtonLikePreviewParameterProvider : PreviewParameterProvider<IconProperty.Vector> {
    override val values = sequenceOf(
        favoriteButtonPreviewSample,
        localPhoneButtonPreviewSample,
    )
}

internal val favoriteButtonPreviewSample = IconProperty.Vector(
    imageVector = Icons.Outlined.FavoriteBorder,
    tint = ColorProperty.DefaultIcon,
)

internal val localPhoneButtonPreviewSample = IconProperty.Vector(
    imageVector = Icons.Filled.Favorite,
    tint = ColorProperty.FavoriteIcon,
)
