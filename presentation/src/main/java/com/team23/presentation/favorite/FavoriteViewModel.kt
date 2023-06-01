package com.team23.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.usecases.GetAllFavoritesUseCase
import com.team23.domain.usecases.UpdateFavoriteUseCase
import com.team23.presentation.home.mappers.SummarizedRecipeMapper
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
	getAllFavoritesUseCase: GetAllFavoritesUseCase,
	private val updateFavoriteUseCase: UpdateFavoriteUseCase,
	private val summarizedRecipeMapper: SummarizedRecipeMapper,
) : ViewModel() {
	val favorites: Flow<List<SummarizedRecipeUiModel>> =
		getAllFavoritesUseCase.invoke().map { recipes ->
			recipes.map { summarizedRecipeMapper.toUiModel(it) }
		}

	fun onFavoriteClick(recipeId: String) {
		viewModelScope.launch(Dispatchers.IO) {
			updateFavoriteUseCase.invoke(recipeId)
		}
	}
}