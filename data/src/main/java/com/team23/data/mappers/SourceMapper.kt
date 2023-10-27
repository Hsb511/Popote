package com.team23.data.mappers

import com.team23.data.models.BaseRecipeDataModel
import com.team23.domain.recipe.model.RecipeDomainModel
import javax.inject.Inject

class SourceMapper @Inject constructor() {
	fun toDomainSource(baseRecipe: BaseRecipeDataModel?) = when {
		baseRecipe?.isTemporary == true -> RecipeDomainModel.Source.Local.Temporary
		baseRecipe?.isSourceLocal == true -> RecipeDomainModel.Source.Local.Saved
		else -> RecipeDomainModel.Source.Remote
	}
}