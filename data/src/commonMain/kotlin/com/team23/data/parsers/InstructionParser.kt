package com.team23.data.parsers

import com.fleeksoft.ksoup.select.Elements
import com.team23.data.models.InstructionDataModel

internal class InstructionParser {
    fun toInstructionsDataModel(
        recipeId: String,
        rawRecipe: Elements
    ): List<InstructionDataModel> = rawRecipe
        .select("ol")
        .select("li")
        .mapIndexed { index, element ->
            InstructionDataModel(
                recipeId = recipeId,
                label = element.text(),
                order = index,
            )
        }
}
