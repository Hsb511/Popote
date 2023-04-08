package com.example.presentation.home.models

import com.example.design_system.flags.NeuracrFlagProperty
import com.example.design_system.image.NeuracrImageProperty

data class HomeRecipeUiModel(
    val id: String,
    val title: String,
    val imageProperty: NeuracrImageProperty,
    val flagProperty: NeuracrFlagProperty,
)
