package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.team23.data.models.UserDataModel

@Dao
interface UserDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(vararg user: UserDataModel)

	@Transaction
	@Query("SELECT * FROM UserDataModel")
	suspend fun loadAll(): List<UserDataModel>
}
