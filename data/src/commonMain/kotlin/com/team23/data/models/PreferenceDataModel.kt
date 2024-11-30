package com.team23.data.models

data class PreferenceDataModel(
	val id: Long = 0L,
	val label: String,
	val value: Int,
)

internal object PreferenceLabel {
	const val DISPLAY_TYPE = "DISPLAY_TYPE"
}
