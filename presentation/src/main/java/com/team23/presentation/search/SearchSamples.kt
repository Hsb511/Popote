package com.team23.presentation.search

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import com.team23.presentation.R
import com.team23.presentation.search.models.TextFieldUiModel

object SearchSamples {
	internal val previewTextFieldSample = TextFieldUiModel(
		searchValue = "Bretzels",
		onValueChange = { },
		interactionSource = MutableInteractionSource(),
		label = R.string.search_textfield_label,
		placeholder = R.string.search_textfield_placeholder,
		leadingIcon = TextFieldUiModel.IconUiModel.Vector(Icons.Filled.Search),
	)
}
