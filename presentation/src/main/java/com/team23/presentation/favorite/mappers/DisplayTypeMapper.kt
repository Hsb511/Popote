package com.team23.presentation.favorite.mappers

import com.team23.domain.models.PreferenceDomainModel
import com.team23.design_system.display.DisplayType
import javax.inject.Inject

class DisplayTypeMapper @Inject constructor() {
	fun toDisplayTypeUiModel(displayType: PreferenceDomainModel.DisplayType) = when (displayType) {
		PreferenceDomainModel.DisplayType.BigCard -> DisplayType.BigCard
		PreferenceDomainModel.DisplayType.SmallCard -> DisplayType.SmallCard
		PreferenceDomainModel.DisplayType.List -> DisplayType.List
	}

	fun toDisplayTypeDomainModel(displayType: DisplayType) = when(displayType) {
		DisplayType.BigCard -> PreferenceDomainModel.DisplayType.BigCard
		DisplayType.SmallCard -> PreferenceDomainModel.DisplayType.SmallCard
		DisplayType.List -> PreferenceDomainModel.DisplayType.List
	}
}
