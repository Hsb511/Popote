package com.team23.view.mapper

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.Res
import com.team23.view.ic_local_smartphone

class LocalPhoneUiMapper {

    @Composable
    fun toLocalPhoneProperty(onLocalPhoneClick: () -> Unit): CellProperty.LocalPhone =
        CellProperty.LocalPhone(
            iconProperty = IconProperty.Resource(
                drawableResource = Res.drawable.ic_local_smartphone,
                contentDescription = "locally_saved_button_content_description",
                tint = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f),
            ),
            onLocalPhoneClick = onLocalPhoneClick
        )
}
