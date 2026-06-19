package com.team23.neuracrsrecipes.viewmodel

import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.preference.usecase.GetPreferenceDisplayTypeUseCase
import com.team23.domain.preference.usecase.UpdatePreferenceUseCase
import com.team23.neuracrsrecipes.extension.next
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.neuracrsrecipes.mapper.DisplayTypeUiMapper
import com.team23.neuracrsrecipes.mapper.SummarizedRecipeUiMapper
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository,
    private val getPreferenceDisplayTypeUseCase: GetPreferenceDisplayTypeUseCase,
    private val updatePreferenceUseCase: UpdatePreferenceUseCase,
    private val summarizedRecipeUiMapper: SummarizedRecipeUiMapper,
    private val displayTypeUiMapper: DisplayTypeUiMapper,
    private val viewModelScope: CoroutineScope,
    private val snackbarHandler: SnackbarHandler,
) {
    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val uiState: StateFlow<FavoriteUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val displayType =
                displayTypeUiMapper.toDisplayTypeUiModel(getPreferenceDisplayTypeUseCase.invoke())
            favoriteRepository.getAllFavorites().map { recipes ->
                recipes.map { summarizedRecipeUiMapper.toUiModel(it) }
            }.collect { favorites ->
                val currentDisplayType = (_uiState.value as? FavoriteUiState.Data.WithFavorites)?.displayType
                    ?: displayType
                _uiState.value = if (favorites.isEmpty()) {
                    FavoriteUiState.Data.Empty
                } else {
                    FavoriteUiState.Data.WithFavorites(
                        displayType = currentDisplayType,
                        favorites = favorites,
                    )
                }
            }
        }
    }

    fun onFavoriteClick(recipeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.updateFavorite(recipeId)
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
                    displayTypeUiMapper.toDisplayTypeDomainModel(newDisplayType)
                )
            }
        }
    }

    fun onLocalPhoneClick() {
        viewModelScope.launch(Dispatchers.IO) {
            snackbarHandler.showLocalPhoneMessage()
        }
    }

    fun onRemoveAllConfirm() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.clearAllFavorites()
        }
    }
}
