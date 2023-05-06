package com.team23.domain.usecases

import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.RecipeRepository
import com.team23.domain.repositories.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class SearchSummarizedRecipesUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
	private val tagRepository: TagRepository,
) {
	fun invoke(
		searchText: String,
		tagsList: List<String>,
	): Flow<List<RecipeDomainModel.Summarized>> =
		combine(
			recipeRepository.getSummarizedRecipesBySearchText(searchText),
			tagRepository.getRecipeIdByTags(tagsList),
		) { recipes, tags -> recipes
			.filter { recipe -> if (tagsList.isNotEmpty()) tags.contains(recipe.id) else true }
			.sortedBy { it.title }
		}
}
