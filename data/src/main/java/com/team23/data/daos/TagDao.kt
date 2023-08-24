package com.team23.data.daos

import androidx.room.*
import com.team23.data.models.TagDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(vararg tagDataModel: TagDataModel)

	@Query("DELETE FROM TagDataModel WHERE recipeId = :recipeId")
	suspend fun deleteAllByRecipeId(recipeId: String)

	@Transaction
	@Query("SELECT * FROM TagDataModel")
	suspend fun loadAll(): List<TagDataModel>

	@Transaction
	@Query("SELECT recipeId FROM TagDataModel WHERE label IN (:tagLabels)")
	fun getRecipeIdByLabel(tagLabels: List<String>): Flow<List<String>>
}
