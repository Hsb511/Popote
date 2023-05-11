package com.team23.presentation.home.models

import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty

data class SummarizedRecipeUiModel(
    val id: String,
    val title: String,
    val imageProperty: NeuracrImageProperty,
    val flagProperty: NeuracrFlagProperty,
    val isFavorite: Boolean,
)
