package com.team23.domain.usecases

import com.team23.domain.models.RecipeDomainModel
import javax.inject.Inject

class UpdateTempRecipeUseCase @Inject constructor(

) {
	fun invoke(recipe: RecipeDomainModel.Full) {
		println("HUGO - $recipe")
	}
}