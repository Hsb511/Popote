package com.team23.data.dao

import com.team23.data.models.PreferenceDataModel
import data.AppDatabaseQueries

internal class PreferenceDao(
    private val dbQueries: AppDatabaseQueries,
) {

    fun insertOrReplace(preferenceDataModel: PreferenceDataModel) {
        dbQueries.insertPreference(preferenceDataModel.toDbModel())
    }

    fun deleteByLabel(label: String) {
        dbQueries.deletePreferenceByLabel(label)
    }

    fun getPreferenceByLabel(label: String): Int {
        return dbQueries.getPreferenceByLabel(label).executeAsOne().toInt()
    }

    private fun PreferenceDataModel.toDbModel() = data.PreferenceDataModel(
        id = id,
        label = label,
        value_ = value.toLong(),
    )
}
