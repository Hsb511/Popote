package com.example.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.models.SummarizedRecipeDataModel

@Dao
interface RecipeDao {
    @Query("SELECT * FROM SummarizedRecipeDataModel")
    fun getAll(): List<SummarizedRecipeDataModel>

    @Insert
    fun insertAll(vararg summarizedRecipes: SummarizedRecipeDataModel)
}