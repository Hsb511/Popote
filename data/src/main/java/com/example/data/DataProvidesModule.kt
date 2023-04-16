package com.example.data

import android.content.Context
import androidx.room.Room
import com.example.data.daos.*
import com.example.data.datasources.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataProvidesModule {
	@Provides
	@Singleton
	fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
		Room.databaseBuilder(context, AppDatabase::class.java, "neuracr-s_recipes_db")
			.build()

	@Provides
	@Singleton
	fun provideSummarizedRecipeDao(appDatabase: AppDatabase): SummarizedRecipeDao = appDatabase.summarizedRecipeDao()

	@Provides
	@Singleton
	fun provideFullRecipeDao(appDatabase: AppDatabase): RecipeDao = appDatabase.fullRecipeDao()
	@Provides
	@Singleton
	fun provideTagDao(appDatabase: AppDatabase): TagDao = appDatabase.tagDao()
	@Provides
	@Singleton
	fun provideIngredientDao(appDatabase: AppDatabase): IngredientDao = appDatabase.ingredientDao()

	@Provides
	@Singleton
	fun provideInstructionDao(appDatabase: AppDatabase): InstructionDao = appDatabase.instructionDao()
}
