package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.IngredientDomainModel
import com.team23.domain.recipe.model.InstructionDomainModel
import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import java.time.LocalDate
import javax.inject.Inject

class CreateNewRecipeUseCase @Inject constructor() {
	fun invoke(): RecipeDomainModel.Full = RecipeDomainModel.Full(
		id = "",
		title = "",
		imageUrl = "",
		date = LocalDate.now(),
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