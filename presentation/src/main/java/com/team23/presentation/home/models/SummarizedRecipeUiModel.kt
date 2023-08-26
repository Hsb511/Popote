package com.team23.presentation.home.models

import com.team23.design_system.cell.NeuracrCellProperty
import com.team23.design_system.display.DisplayType
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty

data class SummarizedRecipeUiModel(
    val id: String,
    val title: String,
    val imageProperty: NeuracrImageProperty,
    val flagProperty: NeuracrFlagProperty,
    val isFavorite: Boolean,
    val isLocallySaved: Boolean,
)

fun SummarizedRecipeUiModel.toNeuracrCellProperty(
    displayType: DisplayType,
    onFavoriteClick: () -> Unit,
    onLocalPhoneClick: () -> Unit,
) = NeuracrCellProperty(
    displayType = displayType,
    imageProperty = imageProperty,
    title = title,
    flagProperty = flagProperty,
    isFavorite = isFavorite,
    isLocallySaved = isLocallySaved,
    onFavoriteClick = onFavoriteClick,
    onLocalPhoneClick = onLocalPhoneClick,
)
