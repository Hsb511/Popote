package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.IngredientDomainModel
import com.team23.domain.recipe.model.InstructionDomainModel
import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.LocalDate

class CreateNewRecipeUseCaseTest {
	private val createNewRecipeUseCase = CreateNewRecipeUseCase()

	@Test
	fun `When use case is called, Then returns new empty full recipe domain model`() {
		// When
		val result = createNewRecipeUseCase.invoke()

		// Then
		val expected = RecipeDomainModel.Full(
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
		assertEquals(expected, result)
	}
}