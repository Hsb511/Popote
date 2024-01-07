package com.team23.domain.preference.usecase

import com.team23.domain.preference.model.PreferenceDomainModel
import com.team23.domain.preference.repository.PreferenceRepository

class UpdatePreferenceUseCase(
    // private val preferenceRepository: PreferenceRepository,
) {
    suspend fun invoke(preference: PreferenceDomainModel) {
        when (preference) {
            is PreferenceDomainModel.DisplayType -> Unit// preferenceRepository.setDisplayType(preference)
        }
    }
}
