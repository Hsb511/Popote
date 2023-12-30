package com.team23.view.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.extension.stringResource

class FavoriteUiMapper {

    @Composable
    fun toFavoriteIconProperty(isFavorite: Boolean): IconProperty.Vector = IconProperty.Vector(
        imageVector = isFavorite.favoriteImageVector(),
        contentDescription = stringResource("favorite_button_content_description"),
        tint = isFavorite.favoriteTint(),
    )


    private fun Boolean.favoriteImageVector(): ImageVector =
        if (this) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

    @Composable
    private fun Boolean.favoriteTint(): Color =
        if (this) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f)
}