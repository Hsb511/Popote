package com.team23.view.sample.uimodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.neuracrsrecipes.model.uimodel.TextFieldUiModel

internal val previewTextFieldSample = TextFieldUiModel(
    searchValue = "Bretzels",
    onValueChange = { },
    label = "Label",
    placeholder = "Placeholder",
    leadingIcon = IconProperty.Vector(Icons.Filled.Search, ""),
)
