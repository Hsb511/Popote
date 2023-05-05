package com.team23.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.team23.design_system.R
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import com.team23.presentation.search.models.TagUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {
	val searchValue = mutableStateOf("")
	val recipes: List<SummarizedRecipeUiModel> = List(6) {
		SummarizedRecipeUiModel(
			id = "",
			title = "Soupe de courgettes et boursin",
			imageProperty = NeuracrImageProperty.Resource(
				contentDescription = null,
				imageRes = R.drawable.bretzel
			),
			flagProperty = NeuracrFlagProperty.FRENCH,
		)
	}
	val tags: List<TagUiModel> = listOf(
		TagUiModel("soup", true),
		TagUiModel("veggie", true),
		TagUiModel("cocktail", false),
		TagUiModel("drink", false),
		TagUiModel("main", false),
		TagUiModel("italian", true)
	)

	fun onValueChange(newValue: String) {
		searchValue.value = newValue
		// TODO UPDATE RECIPES
	}

	fun onTagSelected(tag: TagUiModel) {
		// TODO EMITS NEW TAGS LIST
	}
}