package com.team23.data

import com.team23.data.datasource.NeuracrWebsiteDataSource
import com.team23.data.mappers.DateMapper
import com.team23.data.mappers.FullRecipeMapper
import com.team23.data.mappers.ImageMapper
import com.team23.data.mappers.IngredientMapper
import com.team23.data.mappers.InstructionMapper
import com.team23.data.mappers.LanguageMapper
import com.team23.data.mappers.PreferenceMapper
import com.team23.data.mappers.SourceMapper
import com.team23.data.mappers.SubtitleMapper
import com.team23.data.mappers.SummarizedRecipeMapper
import com.team23.data.mappers.TagMapper
import com.team23.data.parsers.FullRecipeParser
import com.team23.data.parsers.IngredientParser
import com.team23.data.parsers.InstructionParser
import com.team23.data.parsers.InstructionTitleParser
import com.team23.data.parsers.RecipeParser
import com.team23.data.parsers.SummarizedRecipeParser
import com.team23.data.parsers.TagParser
import com.team23.data.repository.RecipeDataRepository
import com.team23.domain.recipe.repository.RecipeRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    factory { CIO.create() }
    factory { HttpClientFactory(get()) }
    single { provideHttpClient(get()) }
    factory { NeuracrWebsiteDataSource(get()) }

    // Repositories
    singleOf(::RecipeDataRepository) { bind<RecipeRepository>() }

    // Parsers
    factoryOf(::DateMapper)
    factoryOf(::FullRecipeMapper)
    factoryOf(::ImageMapper)
    factoryOf(::FullRecipeParser)
    factoryOf(::IngredientParser)
    factoryOf(::InstructionParser)
    factoryOf(::InstructionTitleParser)
    factoryOf(::RecipeParser)
    factoryOf(::SummarizedRecipeParser)
    factoryOf(::TagParser)

    // Mappers
    factoryOf(::IngredientMapper)
    factoryOf(::InstructionMapper)
    factoryOf(::LanguageMapper)
    factoryOf(::PreferenceMapper)
    factoryOf(::SourceMapper)
    factoryOf(::SubtitleMapper)
    factoryOf(::SummarizedRecipeMapper)
    factoryOf(::TagMapper)
}

private fun provideHttpClient(
    httpClientFactory: HttpClientFactory,
): HttpClient = httpClientFactory.createClient(
    baseUrl = NEURACR_WEBSITE_HOME_URL,
    isDebug = true,
)

internal const val NEURACR_WEBSITE_HOME_URL = "https://neuracr.github.io"
