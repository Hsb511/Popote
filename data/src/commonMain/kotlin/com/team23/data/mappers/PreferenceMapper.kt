package com.team23.data.mappers

import com.team23.data.models.PreferenceDataModel
import com.team23.data.models.PreferenceLabel.DISPLAY_TYPE
import com.team23.domain.preference.model.PreferenceDomainModel

class PreferenceMapper {
	fun toDomainDisplayType(preferenceValue: Int) = when (preferenceValue) {
		0 -> PreferenceDomainModel.DisplayType.BigCard
		1 -> PreferenceDomainModel.DisplayType.SmallCard
		2 -> PreferenceDomainModel.DisplayType.List
		else -> PreferenceDomainModel.DisplayType.BigCard
	}

	fun toDataDisplayType(displayType: PreferenceDomainModel.DisplayType) = PreferenceDataModel(
		label = DISPLAY_TYPE,
		value = when (displayType) {
			PreferenceDomainModel.DisplayType.BigCard -> 0
			PreferenceDomainModel.DisplayType.SmallCard -> 1
			PreferenceDomainModel.DisplayType.List -> 2
		}
	)
}
