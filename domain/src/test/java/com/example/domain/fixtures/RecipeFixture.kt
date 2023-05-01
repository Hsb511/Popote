package com.example.domain.fixtures

import com.example.domain.models.RecipeDomainModel
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
