package com.team23.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.usecases.GetAndSortAllTagsUseCase
import com.team23.domain.usecases.SearchSummarizedRecipesUseCase
import com.team23.presentation.home.mappers.SummarizedRecipeMapper
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
	private val searchSummarizedRecipesUseCase: SearchSummarizedRecipesUseCase,
	private val tagMapper: TagMapper,
	private val summarizedRecipeMapper: SummarizedRecipeMapper,
) : ViewModel() {
	val searchValue = mutableStateOf("")

	private val _recipes = MutableStateFlow<List<SummarizedRecipeUiModel>>(emptyList())
	val recipes: StateFlow<List<SummarizedRecipeUiModel>> = _recipes

	private val _tags = MutableStateFlow<List<TagUiModel>>(emptyList())
	val tags: StateFlow<List<TagUiModel>> = _tags

	init {
		viewModelScope.launch(Dispatchers.IO) {
			_tags.value = tagMapper.toTagUiModels(getAndSortAllTagsUseCase.invoke())
		}
		searchNewRecipes()
	}

	fun onValueChange(newValue: String) {
		searchValue.value = newValue
		searchNewRecipes(searchText = newValue)
	}

	fun onTagSelected(tag: TagUiModel) {
		val newTags = _tags.value.filter { it.label != tag.label } + tag.copy(isSelected = !tag.isSelected)
		val (selected, unselected) = newTags.partition { it.isSelected }
		val recomputedTags = selected.sortedBy { it.label } + unselected.sortedBy { it.label }
		_tags.value = recomputedTags
		searchNewRecipes(tagsList = recomputedTags.filterAndMap())
	}

	private fun searchNewRecipes(
		searchText: String = searchValue.value,
		tagsList: List<String> = tags.value.filterAndMap(),
	) {
		viewModelScope.launch(Dispatchers.IO) {
			searchSummarizedRecipesUseCase.invoke(
				searchText = searchText,
				tagsList = tagsList,
			).collect { recipes ->
				_recipes.value = recipes.map { recipe ->
					summarizedRecipeMapper.toUiModel(recipe)
				}
			}
		}
	}

	private fun List<TagUiModel>.filterAndMap() = filter { it.isSelected }.map { it.label }
}
