package com.team23.view.mapper

import androidx.compose.ui.unit.Dp
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel

class RecipeUiMapper {
    private val localPhoneUiMapper = LocalPhoneUiMapper()
    private val favoriteUiMapper = FavoriteUiMapper()

    fun toCellProperty(
        recipe: SummarizedRecipeUiModel,
        displayType: DisplayType,
        maxHeight: Dp? = null,
    ): CellProperty = CellProperty(
        id = recipe.id,
        displayType = displayType,
        imageProperty = recipe.imageProperty,
        title = recipe.title,
        languageFlag = recipe.flagProperty,
        isLocallySaved = recipe.isLocallySaved,
        localPhone = localPhoneUiMapper.toLocalPhoneProperty(),
        favorite = CellProperty.Favorite(
            iconProperty = favoriteUiMapper.toFavoriteIconProperty(recipe.isFavorite),
        ),
        maxHeight = maxHeight,
    )
}
