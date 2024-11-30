package com.team23.view.mapper

import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel

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
        localPhone = LocalPhoneUiMapper().toLocalPhoneProperty(onLocalPhoneClick),
        favorite = CellProperty.Favorite(
            iconProperty = FavoriteUiMapper().toFavoriteIconProperty(recipe.isFavorite),
            onFavoriteClick = onFavoriteClick
        ),
    )
}
