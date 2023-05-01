package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.team23.data.models.SummarizedRecipeDataModel

@Dao
interface SummarizedRecipeDao {
    @Query("SELECT * FROM SummarizedRecipeDataModel")
    suspend fun getAll(): List<SummarizedRecipeDataModel>

    @Insert
    suspend fun insertAll(vararg summarizedRecipes: SummarizedRecipeDataModel)
}