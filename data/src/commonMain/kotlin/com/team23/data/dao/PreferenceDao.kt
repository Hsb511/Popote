package com.team23.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.team23.data.models.PreferenceDataModel
import data.AppDatabaseQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PreferenceDao(
    private val dbQueries: AppDatabaseQueries,
) {

    fun insertOrReplace(preferenceDataModel: PreferenceDataModel) {
        dbQueries.insertPreference(preferenceDataModel.toDbModel())
    }

    fun deleteByLabel(label: String) {
        dbQueries.deletePreferenceByLabel(label)
    }

    fun getPreferenceByLabel(label: String): Flow<Long> =
        dbQueries.getPreferenceByLabel(label)
            .asFlow()
            .mapToOneOrNull(Dispatchers.Default)
            .map { it ?: 0L }

    private fun PreferenceDataModel.toDbModel() = data.PreferenceDataModel(
        id = id,
        label = label,
        value_ = value.toLong(),
    )
}
