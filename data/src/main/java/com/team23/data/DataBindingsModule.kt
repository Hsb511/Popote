package com.team23.data

import com.team23.data.repositories.RecipeRepositoryImpl
import com.team23.data.repositories.TagRepositoryImpl
import com.team23.domain.repositories.RecipeRepository
import com.team23.domain.repositories.TagRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataBindingsModule {
    @Binds
    internal abstract fun bindRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    internal abstract fun bindTagRepository(impl: TagRepositoryImpl): TagRepository
}
