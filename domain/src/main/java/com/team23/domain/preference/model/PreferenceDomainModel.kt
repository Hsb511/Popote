package com.team23.domain.preference.model

sealed class PreferenceDomainModel {
	sealed class DisplayType: PreferenceDomainModel() {
		object BigCard: DisplayType()
		object SmallCard: DisplayType()
		object List: DisplayType()
	}
}
