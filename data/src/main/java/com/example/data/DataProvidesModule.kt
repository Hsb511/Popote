package com.example.data

import android.content.Context
import androidx.room.Room
import com.example.data.daos.RecipeDao
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
    fun provideStationDao(appDatabase: AppDatabase): RecipeDao = appDatabase.recipeDao()
}
