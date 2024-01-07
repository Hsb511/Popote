package com.team23.domain

import com.team23.domain.favorite.usecase.UpdateFavoriteUseCase
import com.team23.domain.recipe.usecase.CreateNewRecipeUseCase
import com.team23.domain.recipe.usecase.DeleteRecipeUseCase
import com.team23.domain.recipe.usecase.GetFullRecipeByIdUseCase
import com.team23.domain.recipe.usecase.LoadTemporaryRecipeUseCase
import com.team23.domain.recipe.usecase.SaveRecipeUseCase
import com.team23.domain.recipe.usecase.SetRecipeBackToTempUseCase
import com.team23.domain.recipe.usecase.UpdateTempRecipeUseCase
import com.team23.domain.tag.usecase.GetAndSortAllTagsUseCase
import com.team23.domain.user.usecase.GetExistingNicknamesUseCase
import com.team23.domain.user.usecase.GetUserNicknameUseCase
import com.team23.domain.user.usecase.SetUserNicknameUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    // Recipe
    factoryOf(::CreateNewRecipeUseCase)
    factoryOf(::DeleteRecipeUseCase)
    factoryOf(::GetAndSortAllTagsUseCase)
    factoryOf(::GetFullRecipeByIdUseCase)
    factoryOf(::LoadTemporaryRecipeUseCase)
    factoryOf(::SaveRecipeUseCase)
    factoryOf(::SetRecipeBackToTempUseCase)
    factoryOf(::UpdateFavoriteUseCase)
    factoryOf(::UpdateTempRecipeUseCase)

    // User
    factoryOf(::GetUserNicknameUseCase)
    factoryOf(::GetExistingNicknamesUseCase)
    factoryOf(::SetUserNicknameUseCase)
}
