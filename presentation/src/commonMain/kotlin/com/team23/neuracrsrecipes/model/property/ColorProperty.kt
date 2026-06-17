package com.team23.neuracrsrecipes.model.property

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme

sealed interface ColorProperty {
    @Composable fun materialColor(): Color

    data object Unspecified : ColorProperty {
        @Composable override fun materialColor(): Color = Color.Unspecified
    }

    data object FavoriteIcon: ColorProperty {
        @Composable override fun materialColor(): Color = MaterialTheme.colorScheme.error
    }

    data object DefaultIcon: ColorProperty {
        @Composable override fun materialColor(): Color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f)
    }

    data object AccentIcon: ColorProperty {
        @Composable override fun materialColor(): Color = MaterialTheme.colorScheme.onSecondaryContainer
    }
}
