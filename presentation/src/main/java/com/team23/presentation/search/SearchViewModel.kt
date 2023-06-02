package com.team23.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.usecases.GetAndSortAllTagsUseCase
import com.team23.domain.usecases.SearchSummarizedRecipesUseCase
import com.team23.domain.usecases.UpdateFavoriteUseCase
import com.team23.presentation.home.mappers.SummarizedRecipeMapper
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import com.team23.presentation.search.mappers.TagMapper
import com.team23.presentation.search.models.TagUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val getAndSortAllTagsUseCase: GetAndSortAllTagsUseCase,
	private val searchSummarizedRecipesUseCase: SearchSummarizedRecipesUseCase,
	private val updateFavoriteUseCase: UpdateFavoriteUseCase,
	private val tagMapper: TagMapper,
	private val summarizedRecipeMapper: SummarizedRecipeMapper,
) : ViewModel() {
	val searchValue = mutableStateOf("")

	private val _recipes = MutableStateFlow<List<SummarizedRecipeUiModel>>(emptyList())
	val recipes: StateFlow<List<SummarizedRecipeUiModel>> = _recipes

	private val _tags = MutableStateFlow<List<TagUiModel>>(emptyList())
	val tags: StateFlow<List<TagUiModel>> = _tags

	var selectedTag: String? = null

	init {
		viewModelScope.launch(Dispatchers.IO) {
			val tags = tagMapper.toTagUiModels(getAndSortAllTagsUseCase.invoke())
			withContext(Dispatchers.Main) { _tags.value = tags }
			selectedTag?.let { tag ->
				onTagSelected(TagUiModel(label = tag, isSelected = false))
			}
		}
		searchNewRecipes()
	}

	fun onValueChange(newValue: String) {
		searchValue.value = newValue
		searchNewRecipes(searchText = newValue)
	}

	fun onTagSelected(tag: TagUiModel) {
		val tagIndex = _tags.value.indexOf(tag)
		val newTags = _tags.value.toMutableList()
		newTags.removeAt(tagIndex)
		newTags.add(tagIndex, tag.copy(isSelected = !tag.isSelected))
		_tags.value = newTags
		searchNewRecipes(tagsList = newTags)
	}

	private fun searchNewRecipes(
		searchText: String = searchValue.value,
		tagsList: List<TagUiModel> = tags.value,
	) {
		viewModelScope.launch(Dispatchers.IO) {
			searchSummarizedRecipesUseCase.invoke(
				searchText = searchText,
				tagsList = tagsList.filter { it.isSelected }.map { it.label },
			).collect { recipes ->
				val recipeUiModels = recipes.map { recipe -> summarizedRecipeMapper.toUiModel(recipe) }
				withContext(Dispatchers.Main) { _recipes.value = recipeUiModels }
			}
		}
	}

	fun favoriteClick(recipeId: String) {
		viewModelScope.launch(Dispatchers.IO) {
			updateFavoriteUseCase.invoke(recipeId)
		}
	}
}
