package com.team23.domain.repositories

import com.team23.domain.models.PreferenceDomainModel.DisplayType

interface PreferenceRepository {
	suspend fun setDisplayType(displayType: DisplayType)
	suspend fun getDisplayType(): DisplayType
}
