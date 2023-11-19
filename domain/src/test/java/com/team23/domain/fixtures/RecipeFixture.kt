package com.team23.domain.fixtures

import com.team23.domain.recipe.model.IngredientDomainModel
import com.team23.domain.recipe.model.InstructionDomainModel
import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import java.time.LocalDate

fun getEmptySummarizedRecipe(id: String = "") = RecipeDomainModel.Summarized(
    id = id,
    imageUrl = "",
    title = "",
    date = LocalDate.MIN,
    language = LanguageDomainModel.FRENCH,
    isFavorite = false,
    source = RecipeDomainModel.Source.Local.Temporary,
)

fun getEmptyFullRecipe(id: String = "") = RecipeDomainModel.Full(
    id = id,
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