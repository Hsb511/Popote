package com.team23.data

import com.team23.data.repositories.RecipeDataRepository
import com.team23.domain.repositories.RecipeRepository
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
