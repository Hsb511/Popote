package com.example.data.daos

import androidx.room.*
import com.example.data.models.FullRecipeDataModel
import com.example.data.models.RecipeDataModel

@Dao
interface RecipeDao {
	@Transaction
	@Query("SELECT * FROM RecipeDataModel WHERE href=:recipeId")
	suspend fun findFullRecipeById(recipeId: String): FullRecipeDataModel?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(vararg recipeDataModel: RecipeDataModel)
}
