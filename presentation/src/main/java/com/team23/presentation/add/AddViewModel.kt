package com.team23.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.usecases.CreateNewRecipeUseCase
import com.team23.domain.usecases.GetAndSortAllTagsUseCase
import com.team23.presentation.add.models.AddRecipeUiModel
import com.team23.presentation.recipe.mappers.RecipeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
	createNewRecipeUseCase: CreateNewRecipeUseCase,
	recipeMapper: RecipeMapper,
	private val getAndSortAllTagsUseCase: GetAndSortAllTagsUseCase,
) : ViewModel() {
	private val _recipe = MutableStateFlow(recipeMapper.toRecipeUiModel(createNewRecipeUseCase.invoke()))

	val initialRecipe: AddRecipeUiModel = AddRecipeUiModel(
		recipe = _recipe.value,
		onTitleChange = { newTitle -> onTitleChange(newTitle) },
		onAuthorChange = { newAuthor -> onAuthorChange(newAuthor) },
		onAddTag = { newTag -> onAddTag(newTag) },
		onRemoveTag = { tag -> onRemoveTag(tag) },
		onAddIngredient = {},
		onServingsAmountChange = {},
		onAddOneServing = {},
		onSubtractOneServing = {},
		onDescriptionChange = { newDescription -> onDescriptionChange(newDescription) },
		onConclusionChange = { newConclusion -> onConclusionChange(newConclusion) }
	)

	private val _tags = MutableStateFlow<List<String>>(emptyList())
	val tags: StateFlow<List<String>> = _tags

	init {
		viewModelScope.launch(Dispatchers.IO) {
			_tags.value = getAndSortAllTagsUseCase.invoke()
		}
	}

	private fun onTitleChange(newTitle: String) {
		_recipe.value = _recipe.value.copy(title = newTitle)
	}

	private fun onAuthorChange(newAuthor: String) {
		_recipe.value = _recipe.value.copy(author = newAuthor)
	}

	private fun onAddTag(newTag: String) {
		_recipe.value = _recipe.value.copy(tags = _recipe.value.tags + newTag)
		val tags = _tags.value.toMutableList()
		tags.remove(newTag)
		_tags.value = tags
	}

	private fun onRemoveTag(tag: String) {
		val currentTags = _recipe.value.tags.toMutableList()
		currentTags.remove(tag)
		_recipe.value = _recipe.value.copy(tags = currentTags)
		_tags.value = _tags.value + tag
	}

	private fun onDescriptionChange(newDescription: String) {
		_recipe.value = _recipe.value.copy(description = newDescription)
	}

	private fun onConclusionChange(newConclusion: String) {
		_recipe.value = _recipe.value.copy(conclusion = newConclusion)
	}
}
