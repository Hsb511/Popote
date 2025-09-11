package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.IngredientDomainModel
import com.team23.domain.recipe.model.InstructionDomainModel
import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class CreateNewRecipeUseCase {

	@OptIn(ExperimentalTime::class)
    fun invoke(): RecipeDomainModel.Full = RecipeDomainModel.Full(
		id = TEMP_RECIPE_ID,
		title = "",
		imageUrl = "",
		date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
		language = LanguageDomainModel.FRENCH,
		isFavorite = false,
		author = "",
		tags = listOf(),
		servingsNumber = 1,
		ingredients = listOf(IngredientDomainModel.WithoutQuantity("")),
		startingText = "",
		instructions = listOf(InstructionDomainModel(order = 1, label = "")),
		endingText = "",
		sections = listOf(),
		source = RecipeDomainModel.Source.Local.Temporary,
	)
}
