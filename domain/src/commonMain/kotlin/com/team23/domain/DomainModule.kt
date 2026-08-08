package com.team23.domain

import com.team23.domain.preference.usecase.GetPreferenceDisplayTypeUseCase
import com.team23.domain.preference.usecase.UpdatePreferenceUseCase
import com.team23.domain.recipe.usecase.CreateNewRecipeUseCase
import com.team23.domain.recipe.usecase.DeleteRecipeUseCase
import com.team23.domain.recipe.usecase.GetAllSummarizedRecipesUseCase
import com.team23.domain.recipe.usecase.GetFullRecipeByIdUseCase
import com.team23.domain.recipe.usecase.GetPromotedLanesUseCase
import com.team23.domain.recipe.usecase.LoadTemporaryRecipeUseCase
import com.team23.domain.recipe.usecase.OverwriteAllSummarizedRecipesUseCase
import com.team23.domain.recipe.usecase.SaveRecipeUseCase
import com.team23.domain.recipe.usecase.SearchSummarizedRecipesUseCase
import com.team23.domain.recipe.usecase.SetRecipeBackToTempUseCase
import com.team23.domain.recipe.usecase.UpdateTempRecipeUseCase
import com.team23.domain.tag.usecase.GetAndSortAllTagsUseCase
import com.team23.domain.user.usecase.GetExistingNicknamesUseCase
import com.team23.domain.user.usecase.GetUserNicknameUseCase
import com.team23.domain.user.usecase.SetUserNicknameUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    // Preference
    factoryOf(::GetPreferenceDisplayTypeUseCase)
    factoryOf(::UpdatePreferenceUseCase)

    // Recipe
    factoryOf(::CreateNewRecipeUseCase)
    factoryOf(::DeleteRecipeUseCase)
    factoryOf(::GetAllSummarizedRecipesUseCase)
    factoryOf(::OverwriteAllSummarizedRecipesUseCase)
    factoryOf(::GetFullRecipeByIdUseCase)
    factoryOf(::GetPromotedLanesUseCase)
    factoryOf(::LoadTemporaryRecipeUseCase)
    factoryOf(::SaveRecipeUseCase)
    factoryOf(::SetRecipeBackToTempUseCase)
    factoryOf(::UpdateTempRecipeUseCase)

    // Search
    factoryOf(::SearchSummarizedRecipesUseCase)

    // Tag
    factoryOf(::GetAndSortAllTagsUseCase)

    // User
    factoryOf(::GetUserNicknameUseCase)
    factoryOf(::GetExistingNicknamesUseCase)
    factoryOf(::SetUserNicknameUseCase)
}
