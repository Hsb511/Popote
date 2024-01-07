package com.team23.neuracrsrecipes.mapper

import com.team23.domain.preference.model.PreferenceDomainModel
import com.team23.neuracrsrecipes.model.property.DisplayType

class DisplayTypeMapper {

    fun toDisplayTypeUiModel(displayType: PreferenceDomainModel.DisplayType) = when (displayType) {
        PreferenceDomainModel.DisplayType.BigCard -> DisplayType.BigCard
        PreferenceDomainModel.DisplayType.SmallCard -> DisplayType.SmallCard
        PreferenceDomainModel.DisplayType.List -> DisplayType.List
    }

    fun toDisplayTypeDomainModel(displayType: DisplayType) = when (displayType) {
        DisplayType.BigCard -> PreferenceDomainModel.DisplayType.BigCard
        DisplayType.SmallCard -> PreferenceDomainModel.DisplayType.SmallCard
        DisplayType.List -> PreferenceDomainModel.DisplayType.List
    }
}
