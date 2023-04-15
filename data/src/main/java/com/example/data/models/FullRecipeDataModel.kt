package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FullRecipeDataModel(
	@PrimaryKey val href: String,
	val imgSrc: String,
	val title: String,
	val subTitle: String,
	// TODO TAGS
	// TODO INGREDIENTS
	val instructionTitle: String,
	// TODO INSTRUCTIONS
	val lastTitle: String,
)
