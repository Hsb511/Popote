package com.team23.domain.fixtures

import com.team23.domain.recipe.model.RecipeDomainModel
import java.time.LocalDate

fun getEmptySummarizedRecipe(id: String = "") = RecipeDomainModel.Summarized(
    id = id,
    imageUrl = "",
    title = "",
    date = LocalDate.MIN,
)

fun getEmptyFullRecipe(id: String = "") = RecipeDomainModel.Full(
    id = id,
    imageUrl = "",
    title = "",
    date = LocalDate.MIN,
    author = "",
    tags = listOf(),
    servingsNumber = 0,
    ingredients = listOf(),
    startingText = "",
    instructions = listOf(),
    endingText = "",
    sections = listOf()
)
