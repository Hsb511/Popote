package com.team23.neuracrsrecipes.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.recipe.usecase.SearchSummarizedRecipesUseCase
import com.team23.domain.tag.usecase.GetAndSortAllTagsUseCase
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.neuracrsrecipes.mapper.SummarizedRecipeUiMapper
import com.team23.neuracrsrecipes.mapper.TagUiMapper
import com.team23.neuracrsrecipes.model.action.SearchAction
import com.team23.neuracrsrecipes.model.event.SearchUiEvent
import com.team23.neuracrsrecipes.model.uimodel.SnackbarResultUiModel
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val getAndSortAllTagsUseCase: GetAndSortAllTagsUseCase,
    private val searchSummarizedRecipesUseCase: SearchSummarizedRecipesUseCase,
    private val favoriteRepository: FavoriteRepository,
    private val tagUiMapper: TagUiMapper,
    private val summarizedRecipeUiMapper: SummarizedRecipeUiMapper,
    private val viewModelScope: CoroutineScope,
    private val snackbarHandler: SnackbarHandler,
) {
    val searchValue = mutableStateOf("")

    private val _recipes = MutableStateFlow<List<SummarizedRecipeUiModel>>(emptyList())
    val recipes: StateFlow<List<SummarizedRecipeUiModel>> = _recipes

    private val _tags = MutableStateFlow<List<TagUiModel>>(emptyList())
    val tags: StateFlow<List<TagUiModel>> = _tags

    private val _uiEvent = MutableSharedFlow<SearchUiEvent>()
    val uiEvent: SharedFlow<SearchUiEvent> = _uiEvent

    var selectedTag: TagUiModel? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val tags = tagUiMapper.toTagUiModels(getAndSortAllTagsUseCase.invoke())
            withContext(Dispatchers.Main) { _tags.value = tags }
            selectedTag?.let { tag ->
                onTagSelected(tag)
            }
        }
        searchNewRecipes()
    }

    fun handleSearchAction(action: SearchAction) {
        when(action) {
            is SearchAction.SearchValueChange -> onValueChange(action.value)
            is SearchAction.TagClick -> onTagSelected(action.tag)
            is SearchAction.FavoriteClick -> favoriteClick(action)
            is SearchAction.RecipeClick -> recipeClick(action.recipeId)
            is SearchAction.LocalPhoneClick -> onLocalPhoneClick()
        }
    }

    private fun onValueChange(newValue: String) {
        searchValue.value = newValue
        searchNewRecipes(searchText = newValue)
    }

    private fun onTagSelected(tag: TagUiModel) {
        val tagIndex = _tags.value.indexOf(tag)
        val newTags = _tags.value.toMutableList()
        newTags.removeAt(tagIndex)
        val newTag = when (tag) {
            is TagUiModel.Flag -> tag.copy(isSelected = !tag.isSelected)
            is TagUiModel.Label -> tag.copy(isSelected = !tag.isSelected)
        }
        newTags.add(tagIndex, newTag)
        _tags.value = newTags
        searchNewRecipes(tagsList = newTags)
    }

    private fun onLocalPhoneClick() {
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
                    recipes.map { recipe -> summarizedRecipeUiMapper.toUiModel(recipe) }
                withContext(Dispatchers.Main) { _recipes.value = recipeUiModels }
            }
        }
    }

    private fun favoriteClick(action: SearchAction.FavoriteClick) {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = favoriteRepository.updateFavorite(action.recipeId)
            recomputeState(action.recipeId)
            if (isFavorite) {
                val result = snackbarHandler.showFavoriteMessage(action.recipeTitle)
                if (result == SnackbarResultUiModel.ActionPerformed) {
                    favoriteRepository.updateFavorite(action.recipeId)
                    recomputeState(action.recipeId)
                }
            }
        }
    }

    private fun recipeClick(recipeId: String) {
        viewModelScope.launch {
            _uiEvent.emit(SearchUiEvent.NavigateToRecipe(recipeId))
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
