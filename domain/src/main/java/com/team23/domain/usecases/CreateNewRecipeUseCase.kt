package com.team23.domain.usecases

import com.team23.domain.models.LanguageDomainModel
import com.team23.domain.models.RecipeDomainModel
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
		ingredients = listOf(),
		startingText = "",
		instructions = listOf(),
		endingText = "",
		sections = listOf(),
	)
}