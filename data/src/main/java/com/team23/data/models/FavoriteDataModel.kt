package com.team23.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteDataModel(
	@PrimaryKey(autoGenerate = true) val id: Long = 0L,
	val recipeId: String,
)
