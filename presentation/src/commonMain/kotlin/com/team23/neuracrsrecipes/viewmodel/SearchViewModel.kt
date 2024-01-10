package com.team23.neuracrsrecipes.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.team23.domain.favorite.usecase.UpdateFavoriteUseCase
import com.team23.domain.recipe.usecase.SearchSummarizedRecipesUseCase
import com.team23.domain.tag.usecase.GetAndSortAllTagsUseCase
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.neuracrsrecipes.mapper.SummarizedRecipeMapper
import com.team23.neuracrsrecipes.mapper.TagMapper
import com.team23.neuracrsrecipes.model.uimodel.SnackbarResultUiModel
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val getAndSortAllTagsUseCase: GetAndSortAllTagsUseCase,
    private val searchSummarizedRecipesUseCase: SearchSummarizedRecipesUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    private val tagMapper: TagMapper,
    private val summarizedRecipeMapper: SummarizedRecipeMapper,
    private val viewModelScope: CoroutineScope,
    private val snackbarHandler: SnackbarHandler,
) {
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

    fun onLocalPhoneClick() {
        viewModelScope.launch(Dispatchers.IO) {
            snackbarHandler.showLocalPhoneMessage()
        }
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
                val recipeUiModels =
                    recipes.map { recipe -> summarizedRecipeMapper.toUiModel(recipe) }
                withContext(Dispatchers.Main) { _recipes.value = recipeUiModels }
            }
        }
    }

    fun favoriteClick(recipe: SummarizedRecipeUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            updateFavoriteUseCase.invoke(recipe.id)
            recomputeState(recipe.id)
            if (!recipe.isFavorite) {
                val result = snackbarHandler.showFavoriteMessage(recipe.title)
                if (result == SnackbarResultUiModel.ActionPerformed) {
                    updateFavoriteUseCase.invoke(recipe.id)
                    recomputeState(recipe.id)
                }
            }
        }
    }

    private fun recomputeState(recipeId: String) {
        val currentState = _recipes.value
        val newRecipes = currentState.toMutableList()
        val recipe = newRecipes.first { recipe -> recipe.id == recipeId }
        val recipeIndex = newRecipes.indexOf(recipe)
        newRecipes.remove(recipe)
        newRecipes.add(recipeIndex, recipe.copy(isFavorite = !recipe.isFavorite))
        _recipes.value = newRecipes
    }
}
