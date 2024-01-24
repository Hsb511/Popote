package com.team23.neuracrsrecipes

import com.team23.neuracrsrecipes.mapper.DateUiMapper
import com.team23.neuracrsrecipes.mapper.DisplayTypeUiMapper
import com.team23.neuracrsrecipes.mapper.ImageUiMapper
import com.team23.neuracrsrecipes.mapper.IngredientUiMapper
import com.team23.neuracrsrecipes.mapper.InstructionUiMapper
import com.team23.neuracrsrecipes.mapper.RecipeUiMapper
import com.team23.neuracrsrecipes.mapper.SummarizedRecipeUiMapper
import com.team23.neuracrsrecipes.mapper.TagUiMapper
import com.team23.neuracrsrecipes.viewmodel.AddViewModel
import com.team23.neuracrsrecipes.viewmodel.FavoriteViewModel
import com.team23.neuracrsrecipes.viewmodel.HomeViewModel
import com.team23.neuracrsrecipes.viewmodel.RecipeViewModel
import com.team23.neuracrsrecipes.viewmodel.SearchViewModel
import com.team23.neuracrsrecipes.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.plus
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val presentationModule = module {
    // Coroutine scope
    factory { MainScope() + CoroutineName("user") }

    // Mapper
    factoryOf(::DateUiMapper)
    factoryOf(::DisplayTypeUiMapper)
    factoryOf(::ImageUiMapper)
    factoryOf(::IngredientUiMapper)
    factoryOf(::InstructionUiMapper)
    factoryOf(::RecipeUiMapper)
    factoryOf(::SummarizedRecipeUiMapper)
    factoryOf(::TagUiMapper)

    // View model
    factoryOf(::AddViewModel)
    factoryOf(::FavoriteViewModel)
    factoryOf(::HomeViewModel)
    factoryOf(::RecipeViewModel)
    factoryOf(::SearchViewModel)
    factoryOf(::UserViewModel)
}
