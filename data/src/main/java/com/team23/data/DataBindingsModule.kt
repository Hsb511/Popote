package com.team23.data

import com.team23.data.repositories.FavoriteRepositoryImpl
import com.team23.data.repositories.PreferenceRepositoryImpl
import com.team23.data.repositories.RecipeRepositoryImpl
import com.team23.data.repositories.TagRepositoryImpl
import com.team23.data.repositories.UserRepositoryImpl
import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.preference.repository.PreferenceRepository
import com.team23.domain.recipe.repository.RecipeRepository
import com.team23.domain.tag.repository.TagRepository
import com.team23.domain.user.repository.UserRepository
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

    @Binds
    internal abstract fun bindFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    internal abstract fun bindPreferenceRepository(impl: PreferenceRepositoryImpl): PreferenceRepository

    @Binds
    internal abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}
