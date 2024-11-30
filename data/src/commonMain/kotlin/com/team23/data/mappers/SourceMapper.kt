package com.team23.data.mappers

import com.team23.data.models.BaseRecipeDataModel
import com.team23.domain.recipe.model.RecipeDomainModel

class SourceMapper {
	fun toDomainSource(baseRecipe: BaseRecipeDataModel?) = when {
		baseRecipe?.isTemporary == true -> RecipeDomainModel.Source.Local.Temporary
		baseRecipe?.isSourceLocal == true -> RecipeDomainModel.Source.Local.Saved
		else -> RecipeDomainModel.Source.Remote
	}
}