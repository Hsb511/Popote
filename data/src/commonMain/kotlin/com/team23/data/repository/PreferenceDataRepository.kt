package com.team23.data.repository

import com.team23.data.datasource.NeuracrLocalDataSource
import com.team23.data.mappers.PreferenceMapper
import com.team23.data.models.PreferenceLabel.DISPLAY_TYPE
import com.team23.domain.preference.model.PreferenceDomainModel
import com.team23.domain.preference.repository.PreferenceRepository

internal class PreferenceDataRepository(
	neuracrLocalDataSource: NeuracrLocalDataSource,
	private val preferenceMapper: PreferenceMapper,
) : PreferenceRepository {
	private val preferenceDao = neuracrLocalDataSource.preferenceDao

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
