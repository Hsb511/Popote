package com.example.data.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.daos.RecipeDao
import com.example.data.models.SummarizedRecipeDataModel

@Database(entities = [SummarizedRecipeDataModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}