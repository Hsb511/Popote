package com.team23.neuracrsrecipes.viewmodel

import com.team23.domain.favorite.usecase.GetAllFavoritesUseCase
import com.team23.domain.favorite.usecase.UpdateFavoriteUseCase
import com.team23.domain.preference.usecase.GetPreferenceDisplayTypeUseCase
import com.team23.domain.preference.usecase.UpdatePreferenceUseCase
import com.team23.neuracrsrecipes.extension.next
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.neuracrsrecipes.mapper.DisplayTypeMapper
import com.team23.neuracrsrecipes.mapper.SummarizedRecipeMapper
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoriteViewModel(
    getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    private val getPreferenceDisplayTypeUseCase: GetPreferenceDisplayTypeUseCase,
    private val updatePreferenceUseCase: UpdatePreferenceUseCase,
    private val summarizedRecipeMapper: SummarizedRecipeMapper,
    private val displayTypeMapper: DisplayTypeMapper,
    private val viewModelScope: CoroutineScope,
    private val snackbarHandler: SnackbarHandler,
) {
    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val uiState: StateFlow<FavoriteUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val displayType =
                displayTypeMapper.toDisplayTypeUiModel(getPreferenceDisplayTypeUseCase.invoke())
            getAllFavoritesUseCase.invoke().map { recipes ->
                recipes.map { summarizedRecipeMapper.toUiModel(it) }
            }.collect { favorites ->
                _uiState.value = if (favorites.isEmpty()) {
                    FavoriteUiState.Data.Empty
                } else {
                    FavoriteUiState.Data.WithFavorites(
                        displayType = displayType,
                        favorites = favorites,
                    )
                }
            }
        }
    }

    fun onFavoriteClick(recipeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updateFavoriteUseCase.invoke(recipeId)
        }
    }

    fun onDisplayTypeClick() {
        val currentState = _uiState.value
        if (currentState is FavoriteUiState.Data.WithFavorites) {
            val newDisplayType = currentState.displayType.next()
            _uiState.value = currentState.copy(
                displayType = newDisplayType,
            )
            viewModelScope.launch(Dispatchers.IO) {
                updatePreferenceUseCase.invoke(
                    displayTypeMapper.toDisplayTypeDomainModel(newDisplayType)
                )
            }
        }
    }

    fun onLocalPhoneClick() {
        viewModelScope.launch(Dispatchers.IO) {
            snackbarHandler.showLocalPhoneMessage()
        }
    }
}
