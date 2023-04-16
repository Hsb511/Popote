package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InstructionDataModel(
	@PrimaryKey(autoGenerate = true) val id: Long = 0L,
	val recipeId: String,
	val label: String,
	val number: Int,
)
