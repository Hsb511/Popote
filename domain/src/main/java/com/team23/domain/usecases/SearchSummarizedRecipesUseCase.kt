package com.team23.domain.usecases

import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchSummarizedRecipesUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
) {
	fun invoke(
		searchText: String,
		tagsList: List<String>,
	): Flow<List<RecipeDomainModel.Summarized>> = recipeRepository.getSummarizedRecipesBySearchTextAndTags(
		searchText = searchText,
		tagsList = tagsList,
	)
}
