package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BaseRecipeDataModel(
	@PrimaryKey val href: String,
	val imgSrc: String,
	val title: String,
	val subTitle: String,
	val instructionTitle: String,
	val lastTitle: String,
)
