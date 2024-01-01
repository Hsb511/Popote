package com.team23.domain.preference.model

sealed class PreferenceDomainModel {
	sealed class DisplayType: PreferenceDomainModel() {
		data object BigCard: DisplayType()
		data object SmallCard: DisplayType()
		data object List: DisplayType()
	}
}
