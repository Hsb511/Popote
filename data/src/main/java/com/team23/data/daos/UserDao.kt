package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.team23.data.models.UserDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(vararg user: UserDataModel)

	@Transaction
	@Query("SELECT * FROM UserDataModel LIMIT 1")
	fun getFirst(): Flow<UserDataModel?>
}
