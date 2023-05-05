package com.team23.data.daos

import androidx.room.*
import com.team23.data.models.BaseRecipeDataModel
import com.team23.data.models.FullRecipeDataModel

@Dao
interface RecipeDao {
	@Transaction
	@Query("SELECT * FROM BaseRecipeDataModel WHERE href=:recipeId")
	suspend fun findFullRecipeById(recipeId: String): FullRecipeDataModel?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(vararg baseRecipeDataModel: BaseRecipeDataModel)
}
