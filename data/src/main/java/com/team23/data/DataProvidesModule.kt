package com.team23.data

import android.content.Context
import androidx.room.Room
import com.team23.data.daos.*
import com.team23.data.datasources.AppDatabase
import com.team23.data.datasources.MIGRATION_1_2
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
			.addMigrations(MIGRATION_1_2)
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
	@Provides
	@Singleton
	fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao = appDatabase.favoriteDao()
	@Provides
	@Singleton
	fun providePreferenceDao(appDatabase: AppDatabase): PreferenceDao = appDatabase.preferenceDao()
}
