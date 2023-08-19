package com.team23.presentation.home.mappers

import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.domain.models.LanguageDomainModel
import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.models.RecipeDomainModel.Source
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import javax.inject.Inject

class SummarizedRecipeMapper @Inject constructor() {
	fun toUiModel(summarizedRecipe: RecipeDomainModel.Summarized) = with(summarizedRecipe) {
		SummarizedRecipeUiModel(
			id = id,
			title = title,
			imageProperty = NeuracrImageProperty.Remote(
				contentDescription = title,
				url = imageUrl,
			),
			flagProperty = when (language) {
				LanguageDomainModel.ENGLISH -> NeuracrFlagProperty.UK_US
				LanguageDomainModel.FRENCH -> NeuracrFlagProperty.FRENCH
			},
			isFavorite = isFavorite,
			isLocallySaved = source is Source.Local.Saved,
		)
	}
}
