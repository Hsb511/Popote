package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import com.team23.domain.tag.repository.TagRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.transformLatest

@OptIn(ExperimentalCoroutinesApi::class)
class SearchSummarizedRecipesUseCase(
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
        ) { recipes, tags ->
            recipes
                .filter { recipe -> tagsList.isEmpty() || tags.contains(recipe.id) }
                .sortedBy { it.title }
        }.transformLatest { recipes ->
            emit(recipes)

            val recipesWithCuisine = recipes.map { recipe ->
                val cuisineRegion = tagRepository.getCuisineRegion(recipe.id)
                recipe.copy(cuisineRegion = cuisineRegion)
            }

            emit(recipesWithCuisine)
        }
}
