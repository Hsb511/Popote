package com.team23.neuracrsrecipes

import com.team23.neuracrsrecipes.mapper.DateMapper
import com.team23.neuracrsrecipes.mapper.DisplayTypeMapper
import com.team23.neuracrsrecipes.mapper.ImageMapper
import com.team23.neuracrsrecipes.mapper.IngredientMapper
import com.team23.neuracrsrecipes.mapper.InstructionMapper
import com.team23.neuracrsrecipes.mapper.RecipeMapper
import com.team23.neuracrsrecipes.mapper.SummarizedRecipeMapper
import com.team23.neuracrsrecipes.viewmodel.AddViewModel
import com.team23.neuracrsrecipes.viewmodel.FavoriteViewModel
import com.team23.neuracrsrecipes.viewmodel.RecipeViewModel
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
    factoryOf(::DateMapper)
    factoryOf(::DisplayTypeMapper)
    factoryOf(::ImageMapper)
    factoryOf(::IngredientMapper)
    factoryOf(::InstructionMapper)
    factoryOf(::RecipeMapper)
    factoryOf(::SummarizedRecipeMapper)

    // View model
    factoryOf(::AddViewModel)
    factoryOf(::FavoriteViewModel)
    factoryOf(::RecipeViewModel)
    factoryOf(::UserViewModel)
}
