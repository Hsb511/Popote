package com.team23.data.daos

import androidx.room.*
import com.team23.data.models.TagDataModel

@Dao
interface TagDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(vararg tagDataModel: TagDataModel)

	@Transaction
	@Query("SELECT * FROM TagDataModel")
	suspend fun loadAll(): List<TagDataModel>
}
