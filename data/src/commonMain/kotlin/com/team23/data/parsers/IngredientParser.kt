package com.team23.data.parsers

import com.fleeksoft.ksoup.select.Elements
import com.team23.data.models.IngredientDataModel

internal class IngredientParser {
    fun toIngredientsDataModel(
        recipeId: String,
        rawRecipe: Elements
    ): List<IngredientDataModel> = rawRecipe
        .select("ingredient-item")
        .map { element ->
            IngredientDataModel(
                recipeId = recipeId,
                label = element.attr("name"),
                quantity = element.attr("initialquantity"),
                unit = element.attr("unit"),
            )
        }
}
