package com.team23.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PreferenceDataModel(
	@PrimaryKey(autoGenerate = true) val id: Long = 0L,
	val label: String,
	val value: Int,
)

internal object PreferenceLabel {
	const val DISPLAY_TYPE = "DISPLAY_TYPE"
}
