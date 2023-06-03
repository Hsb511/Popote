package com.team23.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.usecases.GetAllFavoritesUseCase
import com.team23.domain.usecases.GetPreferenceDisplayTypeUseCase
import com.team23.domain.usecases.UpdateFavoriteUseCase
import com.team23.domain.usecases.UpdatePreferenceUseCase
import com.team23.presentation.common.extensions.next
import com.team23.presentation.favorite.mappers.DisplayTypeMapper
import com.team23.presentation.favorite.models.FavoriteUiState
import com.team23.presentation.home.mappers.SummarizedRecipeMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
	getAllFavoritesUseCase: GetAllFavoritesUseCase,
	private val updateFavoriteUseCase: UpdateFavoriteUseCase,
	private val getPreferenceDisplayTypeUseCase: GetPreferenceDisplayTypeUseCase,
	private val updatePreferenceUseCase: UpdatePreferenceUseCase,
	private val summarizedRecipeMapper: SummarizedRecipeMapper,
	private val displayTypeMapper: DisplayTypeMapper,
) : ViewModel() {
	private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
	val uiState: StateFlow<FavoriteUiState> = _uiState

	init {
		viewModelScope.launch(Dispatchers.IO) {
			val displayType = displayTypeMapper.toDisplayTypeUiModel(getPreferenceDisplayTypeUseCase.invoke())
			getAllFavoritesUseCase.invoke().map { recipes ->
				recipes.map { summarizedRecipeMapper.toUiModel(it) }
			}.collect { favorites ->
				_uiState.value = FavoriteUiState.Data(
					displayType = displayType,
					favorites = favorites,
				)
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
		if (currentState is FavoriteUiState.Data) {
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
}
