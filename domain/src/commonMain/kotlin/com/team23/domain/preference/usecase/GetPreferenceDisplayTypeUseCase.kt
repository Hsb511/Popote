package com.team23.domain.preference.usecase

import com.team23.domain.preference.model.PreferenceDomainModel
import com.team23.domain.preference.repository.PreferenceRepository

class GetPreferenceDisplayTypeUseCase(
    // private val preferenceRepository: PreferenceRepository,
) {
    suspend fun invoke(): PreferenceDomainModel.DisplayType = PreferenceDomainModel.DisplayType.BigCard // preferenceRepository.getDisplayType()
}
