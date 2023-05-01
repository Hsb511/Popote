package com.team23.data.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import com.team23.data.daos.*
import com.team23.data.models.*

@Database(
	entities = [
		SummarizedRecipeDataModel::class,
		BaseRecipeDataModel::class,
		TagDataModel::class,
		IngredientDataModel::class,
		InstructionDataModel::class,
	], version = 1
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun summarizedRecipeDao(): SummarizedRecipeDao
	abstract fun fullRecipeDao(): RecipeDao
	abstract fun tagDao(): TagDao
	abstract fun ingredientDao(): IngredientDao
	abstract fun instructionDao(): InstructionDao
}
