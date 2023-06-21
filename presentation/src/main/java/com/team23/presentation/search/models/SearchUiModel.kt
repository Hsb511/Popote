package com.team23.presentation.search.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.graphics.vector.ImageVector
import com.team23.presentation.home.models.SummarizedRecipeUiModel

data class SearchUiModel(
	val textField: TextFieldUiModel,
	val tagsRow: TagsRowUiModel,
	val recipes: List<SummarizedRecipeUiModel>,
	val onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	val onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
)

data class TextFieldUiModel(
	val searchValue: String,
	val onValueChange: (String) -> Unit,
	val interactionSource: MutableInteractionSource,
	@StringRes val label: Int,
	@StringRes val placeholder: Int,
	val leadingIcon: IconUiModel,
	val keyboardActions: KeyboardActions = KeyboardActions(),
) {
	sealed class IconUiModel {
		data class Painter(@DrawableRes val resId: Int): IconUiModel()
		data class Vector(val image: ImageVector): IconUiModel()
	}
}

data class TagsRowUiModel(
	val tags: List<TagUiModel>,
	val onTagSelected: (TagUiModel) -> Unit,
)