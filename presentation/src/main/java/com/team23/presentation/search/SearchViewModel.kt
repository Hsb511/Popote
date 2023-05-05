package com.team23.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.design_system.R
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.domain.usecases.GetAndSortAllTagsUseCase
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import com.team23.presentation.search.mappers.TagMapper
import com.team23.presentation.search.models.TagUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val getAndSortAllTagsUseCase: GetAndSortAllTagsUseCase,
	private val tagMapper: TagMapper,
) : ViewModel() {
	val searchValue = mutableStateOf("")
	val recipes: List<SummarizedRecipeUiModel> = List(6) {
		SummarizedRecipeUiModel(
			id = "/recipes/2022/03/06/bretzels.html",
			title = "Soupe de courgettes et boursin",
			imageProperty = NeuracrImageProperty.Resource(
				contentDescription = null,
				imageRes = R.drawable.bretzel
			),
			flagProperty = NeuracrFlagProperty.FRENCH,
		)
	}
	private val _tags = MutableStateFlow<List<TagUiModel>>(emptyList())
	val tags: StateFlow<List<TagUiModel>> = _tags

	init {
		viewModelScope.launch(Dispatchers.IO) {
			_tags.value = tagMapper.toTagUiModels(getAndSortAllTagsUseCase.invoke())
		}
	}

	fun onValueChange(newValue: String) {
		searchValue.value = newValue
		// TODO UPDATE RECIPES
	}

	fun onTagSelected(tag: TagUiModel) {
		val newTags = _tags.value.filter { it.label != tag.label } + tag.copy(isSelected = !tag.isSelected)
		val (selected, unselected) = newTags.partition { it.isSelected }
		_tags.value = selected.sortedBy { it.label } + unselected.sortedBy { it.label }
	}
}
