package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.team23.data.models.PreferenceDataModel

@Dao
interface PreferenceDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(preferenceDataModel: PreferenceDataModel)

	@Query("DELETE FROM PreferenceDataModel WHERE label = :label")
	suspend fun deleteByLabel(label: String)

	@Query("SELECT value FROM PreferenceDataModel WHERE label = :label")
	fun getPreferenceByLabel(label: String): Int
}
