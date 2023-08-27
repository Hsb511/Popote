package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.team23.data.models.SummarizedRecipeDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SummarizedRecipeDao {
    @Query("SELECT * FROM SummarizedRecipeDataModel")
    suspend fun getAll(): List<SummarizedRecipeDataModel>

    @Query("SELECT COUNT(*) FROM SummarizedRecipeDataModel")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg summarizedRecipes: SummarizedRecipeDataModel)

    @Query("SELECT * FROM SummarizedRecipeDataModel WHERE title LIKE '%' || :search || '%'")
    fun searchBaseRecipeByTitle(search: String): Flow<List<SummarizedRecipeDataModel>>

    @Query("DELETE FROM SummarizedRecipeDataModel WHERE href = :recipeId")
    suspend fun deleteByRecipeId(recipeId: String)
}