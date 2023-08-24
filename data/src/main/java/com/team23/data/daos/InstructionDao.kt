package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.team23.data.models.InstructionDataModel

@Dao
interface InstructionDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(vararg instructionDataModel: InstructionDataModel)

	@Query("DELETE FROM InstructionDataModel WHERE recipeId =:recipeId")
	suspend fun deleteAllByRecipeId(recipeId: String)
}
