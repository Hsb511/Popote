package com.team23.neuracrsrecipes.model.uimodel

data class ErrorPageUiModel(
    val message: String,
    val redirectToWebsite: () -> Unit,
)
