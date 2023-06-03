package com.team23.domain.usecases

import com.team23.domain.models.PreferenceDomainModel
import com.team23.domain.repositories.PreferenceRepository
import javax.inject.Inject

class GetPreferenceDisplayTypeUseCase @Inject constructor(
	private val preferenceRepository: PreferenceRepository,
) {
	suspend fun invoke(): PreferenceDomainModel.DisplayType = preferenceRepository.getDisplayType()
}
