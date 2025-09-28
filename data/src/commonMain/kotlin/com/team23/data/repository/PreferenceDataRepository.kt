package com.team23.data.repository

import com.team23.data.datasource.PopoteLocalDataSource
import com.team23.data.mappers.PreferenceMapper
import com.team23.data.models.PreferenceLabel.DISPLAY_TYPE
import com.team23.domain.preference.model.PreferenceDomainModel
import com.team23.domain.preference.repository.PreferenceRepository

internal class PreferenceDataRepository(
    popoteLocalDataSource: PopoteLocalDataSource,
    private val preferenceMapper: PreferenceMapper,
) : PreferenceRepository {
	private val preferenceDao = popoteLocalDataSource.preferenceDao

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
