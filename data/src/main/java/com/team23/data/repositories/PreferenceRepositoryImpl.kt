package com.team23.data.repositories

import com.team23.data.daos.PreferenceDao
import com.team23.data.mappers.PreferenceMapper
import com.team23.data.models.PreferenceLabel.DISPLAY_TYPE
import com.team23.domain.models.PreferenceDomainModel
import com.team23.domain.repositories.PreferenceRepository
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
	private val preferenceDao: PreferenceDao,
	private val preferenceMapper: PreferenceMapper,
) : PreferenceRepository {
	override suspend fun setDisplayType(displayType: PreferenceDomainModel.DisplayType) {
		preferenceMapper.toDataDisplayType(displayType).also { preferenceDisplayType ->
			preferenceDao.deleteByLabel(preferenceDisplayType.label)
			preferenceDao.insertOrReplace(preferenceDisplayType)
		}
	}

	override suspend fun getDisplayType() = preferenceMapper.toDomainDisplayType(
		preferenceDao.getPreferenceByLabel(DISPLAY_TYPE)
	)

}
