package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import com.team23.domain.tag.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

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
                .filter { recipe -> if (tagsList.isNotEmpty()) tags.contains(recipe.id) else true }
                .sortedBy { it.title }
        }
}
