package com.team23.domain.preference.repository

import com.team23.domain.preference.model.PreferenceDomainModel.DisplayType
import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
	suspend fun setDisplayType(displayType: DisplayType)
	fun getDisplayType(): Flow<DisplayType>
}
