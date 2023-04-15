package com.example.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.FullRecipeDataModel

@Dao
interface FullRecipeDao {
	@Query("SELECT * FROM FullRecipeDataModel WHERE href=:recipeId")
	suspend fun findById(recipeId: String): FullRecipeDataModel?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(vararg fullRecipeDataModel: FullRecipeDataModel)
}
