package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.team23.data.models.InstructionDataModel

@Dao
interface InstructionDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(vararg instructionDataModel: InstructionDataModel)
}
