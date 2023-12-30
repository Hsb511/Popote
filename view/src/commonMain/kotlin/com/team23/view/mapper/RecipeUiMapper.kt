package com.team23.view.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.view.extension.stringResource

class RecipeUiMapper {

    @Composable
    fun toCellProperty(
        recipe: SummarizedRecipeUiModel,
        displayType: DisplayType,
        onFavoriteClick: () -> Unit,
        onLocalPhoneClick: () -> Unit,
        ): CellProperty = CellProperty(
        displayType = displayType,
        imageProperty = recipe.imageProperty,
        title = recipe.title,
        flagProperty = recipe.flagProperty,
        isLocallySaved = recipe.isLocallySaved,
        localPhone = CellProperty.LocalPhone(
            iconProperty = IconProperty.Resource(
                fileName = "drawable/ic_local_smartphone.xml",
                contentDescription = "locally_saved_button_content_description",
                tint = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f),
            ),
            onLocalPhoneClick = onLocalPhoneClick,
        ),
        favorite = CellProperty.Favorite(
            iconProperty = IconProperty.Vector(
                imageVector = recipe.isFavorite.favoriteImageVector(),
                contentDescription = stringResource("favorite_button_content_description"),
                tint = recipe.isFavorite.favoriteTint(),
            ),
            onFavoriteClick = onFavoriteClick
        ),
    )

    private fun Boolean.favoriteImageVector(): ImageVector =
        if (this) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

    @Composable
    private fun Boolean.favoriteTint(): Color =
        if (this) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f)
}
