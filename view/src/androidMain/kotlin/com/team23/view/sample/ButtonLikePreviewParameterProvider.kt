package com.team23.view.sample

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.team23.neuracrsrecipes.model.property.IconProperty

internal class ButtonLikePreviewParameterProvider : PreviewParameterProvider<IconProperty.Vector> {
    override val values = sequenceOf(
        favoriteButtonPreviewSample,
        localPhoneButtonPreviewSample,
    )
}

internal val favoriteButtonPreviewSample = IconProperty.Vector(
    imageVector = Icons.Outlined.FavoriteBorder,
    contentDescription = "",
    tint = Color.Black,
)

internal val localPhoneButtonPreviewSample = IconProperty.Vector(
    imageVector = Icons.Filled.Favorite,
    contentDescription = "",
    tint = Color.Red,
)
