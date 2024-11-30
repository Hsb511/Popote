package com.team23.neuracrsrecipes.model.uimodel

data class ErrorUiModel(
    val message: String,
    val redirectToWebsite: () -> Unit,
)
