package com.team23.domain.recipe.usecase

import com.team23.domain.locale_info.Season
import com.team23.domain.locale_info.SeasonProvider
import com.team23.domain.recipe.model.PromotedLaneDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import com.team23.domain.tag.repository.TagRepository
import kotlinx.coroutines.flow.first

class GetPromotedLanesUseCase(
    private val tagRepository: TagRepository,
    private val recipeRepository: RecipeRepository,
    private val seasonProvider: SeasonProvider,
) {

    lateinit var allSummarizedRecipes: List<RecipeDomainModel.Summarized>

    suspend fun invoke(): List<PromotedLaneDomainModel> = runCatching {
        recipeRepository.loadAllSummarizedRecipesIfNeeded()
        allSummarizedRecipes = recipeRepository.getAllSummarizedRecipes()
        listOf(
            PromotedLaneDomainModel(PromotedLaneDomainModel.Type.Seasonal, filterSeasonal()),
            PromotedLaneDomainModel(PromotedLaneDomainModel.Type.Vegetarian, filterByTag(listOf(VEGETARIAN_TAG, VEGGIE_TAG))),
            PromotedLaneDomainModel(PromotedLaneDomainModel.Type.Vegan, filterByTag(listOf(VEGAN_TAG)))
        )
    }.getOrElse { emptyList() }

    private suspend fun filterSeasonal(): List<RecipeDomainModel.Summarized> {
        val tags = when (seasonProvider.currentSeason()) {
            Season.Spring -> listOf(TEX_MEX_TAG, ITALIAN_TAG)
            Season.Summer -> listOf(DRINK_TAG, COCKTAIL_TAG)
            Season.Autumn -> listOf(PIE_TAG, SOUP_TAG)
            Season.Winter -> listOf(SOUP_TAG)
        }
        return filterByTag(tags)
    }

    private suspend fun filterByTag(tags: List<String>): List<RecipeDomainModel.Summarized> {
        val vegetarianRecipeIds = tagRepository.getRecipeIdByTags(tags).first().distinct()
        return allSummarizedRecipes.filter { recipe ->
            recipe.id in vegetarianRecipeIds
        }
    }

    companion object {
        private const val VEGETARIAN_TAG = "vegetarian"
        private const val VEGGIE_TAG = "veggie"
        private const val VEGAN_TAG = "vegan"
        private const val SOUP_TAG = "soup"
        private const val PIE_TAG = "pie"
        private const val COCKTAIL_TAG = "cocktail"
        private const val DRINK_TAG = "drink"
        private const val TEX_MEX_TAG = "tex-mex"
        private const val ITALIAN_TAG = "italian"
    }
}
