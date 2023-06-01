package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.team23.data.models.FavoriteDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

	@Query("SELECT EXISTS(SELECT * FROM FavoriteDataModel WHERE recipeId = :recipeId)")
	suspend fun isStored(recipeId: String): Boolean

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(favoriteDataModel: FavoriteDataModel)

	@Query("DELETE FROM FavoriteDataModel WHERE recipeId = :recipeId")
	suspend fun delete(recipeId: String)

	@Query("SELECT recipeId FROM FavoriteDataModel")
	fun getAllFavorites(): Flow<List<String>>
}
