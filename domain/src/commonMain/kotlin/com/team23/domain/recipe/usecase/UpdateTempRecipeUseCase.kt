package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository

class UpdateTempRecipeUseCase(
    createNewRecipeUseCase: CreateNewRecipeUseCase,
    private val recipeRepository: RecipeRepository,
) {

    private val initialRecipe = createNewRecipeUseCase.invoke()

    suspend fun invoke(recipe: RecipeDomainModel.Full) {
        if (recipe != initialRecipe) {
            recipeRepository.updateRecipe(recipe)
        }
    }
}

const val TEMP_RECIPE_ID = "TEMP"
