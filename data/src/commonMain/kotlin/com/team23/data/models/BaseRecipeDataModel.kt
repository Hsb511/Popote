package com.team23.data.models

data class BaseRecipeDataModel(
	val href: String,
	val imgSrc: String,
	val title: String,
	val subTitle: String,
	val servingsAmount: Int,
	val instructionTitle: String,
	val lastTitle: String,
	val isSourceLocal: Boolean,
	val isTemporary: Boolean,
)
