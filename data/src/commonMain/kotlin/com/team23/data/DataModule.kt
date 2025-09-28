package com.team23.data

import com.team23.data.datasource.PopoteLocalDataSource
import com.team23.data.datasource.PopoteWebsiteDataSource
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
import com.team23.data.repository.FavoriteDataRepository
import com.team23.data.repository.PreferenceDataRepository
import com.team23.data.repository.RecipeDataRepository
import com.team23.data.repository.TagDataRepository
import com.team23.data.repository.UserDataRepository
import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.preference.repository.PreferenceRepository
import com.team23.domain.recipe.repository.RecipeRepository
import com.team23.domain.tag.repository.TagRepository
import com.team23.domain.user.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    includes(platformModule())
    factory { CIO.create() }
    factory { HttpClientFactory(get()) }
    single { provideHttpClient(get()) }
    singleOf(::PopoteLocalDataSource)
    factory { PopoteWebsiteDataSource(get()) }

    // Repositories
    singleOf(::FavoriteDataRepository) { bind<FavoriteRepository>() }
    singleOf(::PreferenceDataRepository) { bind<PreferenceRepository>() }
    singleOf(::RecipeDataRepository) { bind<RecipeRepository>() }
    singleOf(::TagDataRepository) { bind<TagRepository>() }
    singleOf(::UserDataRepository) { bind<UserRepository>() }

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

internal expect fun platformModule(): Module
internal const val NEURACR_WEBSITE_HOME_URL = "https://neuracr.github.io"
