package com.team23.neuracrsrecipes.model.uimodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.neuracrsrecipes.model.property.ImageProperty

data class SummarizedRecipeUiModel(
    val id: String,
    val title: String,
    val imageProperty: ImageProperty,
    val flagProperty: FlagProperty,
    val isFavorite: Boolean,
    val isLocallySaved: Boolean,
)

/*
@Composable
fun SummarizedRecipeUiModel.toNeuracrCellProperty(
    displayType: DisplayType,
    onFavoriteClick: () -> Unit,
    onLocalPhoneClick: () -> Unit,
) = CellProperty(
    displayType = displayType,
    imageProperty = imageProperty,
    title = title,
    flagProperty = flagProperty,
    isLocallySaved = isLocallySaved,
    localPhone = CellProperty.LocalPhone(

        onLocalPhoneClick = onLocalPhoneClick,
    ),
    favorite = CellProperty.Favorite(
        iconProperty = IconProperty.Vector(
            imageVector = isFavorite.favoriteImageVector(),
            contentDescription = resource().toString(),
            tint = isFavorite.favoriteTint(),
        ),
        onFavoriteClick = onFavoriteClick
    ),
)

private fun Boolean.favoriteImageVector(): ImageVector =
    if (this) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

@Composable
private fun Boolean.favoriteTint(): Color =
    if (this) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f)
*/