package com.example.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.data.models.IngredientDataModel

@Dao
interface IngredientDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(vararg ingredientDataModel: IngredientDataModel)
}