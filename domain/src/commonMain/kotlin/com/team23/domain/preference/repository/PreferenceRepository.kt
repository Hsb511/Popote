package com.team23.domain.preference.repository

import com.team23.domain.preference.model.PreferenceDomainModel.DisplayType

interface PreferenceRepository {
	suspend fun setDisplayType(displayType: DisplayType)
	suspend fun getDisplayType(): DisplayType
}
