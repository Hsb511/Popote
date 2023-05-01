package com.team23.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BaseRecipeDataModel(
	@PrimaryKey val href: String,
	val imgSrc: String,
	val title: String,
	val subTitle: String,
	val servingsAmount: Int,
	val instructionTitle: String,
	val lastTitle: String,
)
