package com.team23.view.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector
import com.team23.neuracrsrecipes.model.property.ColorProperty
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.Res
import com.team23.view.favorite_button_content_description

class FavoriteUiMapper {

    fun toFavoriteIconProperty(isFavorite: Boolean): IconProperty.Vector = IconProperty.Vector(
        imageVector = isFavorite.favoriteImageVector(),
        contentDescription = Res.string.favorite_button_content_description,
        tint = isFavorite.favoriteTint(),
    )

    private fun Boolean.favoriteImageVector(): ImageVector =
        if (this) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

    private fun Boolean.favoriteTint(): ColorProperty =
        if (this) ColorProperty.FavoriteIcon else ColorProperty.DefaultIcon
}