package com.example.data

import com.example.data.repositories.RecipeDataRepository
import com.example.domain.repositories.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataBindingsModule {
    @Binds
    internal abstract fun bindRecipeRepository(impl: RecipeDataRepository): RecipeRepository
}
