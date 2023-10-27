package com.team23.domain.preference.usecase

import com.team23.domain.preference.model.PreferenceDomainModel
import com.team23.domain.preference.repository.PreferenceRepository
import javax.inject.Inject

class GetPreferenceDisplayTypeUseCase @Inject constructor(
	private val preferenceRepository: PreferenceRepository,
) {
	suspend fun invoke(): PreferenceDomainModel.DisplayType = preferenceRepository.getDisplayType()
}
