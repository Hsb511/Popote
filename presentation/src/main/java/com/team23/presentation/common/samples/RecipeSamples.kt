package com.team23.presentation.common.samples

import com.team23.design_system.R
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.presentation.home.models.SummarizedRecipeUiModel

internal object RecipeSamples {
	val summarizedRecipeSample = SummarizedRecipeUiModel(
		id = "",
		imageProperty = NeuracrImageProperty.Resource(
			contentDescription = null,
			imageRes = R.drawable.bretzel
		),
		title = "Bretzels",
		flagProperty = NeuracrFlagProperty.FRENCH,
		isFavorite = true,
	)
}