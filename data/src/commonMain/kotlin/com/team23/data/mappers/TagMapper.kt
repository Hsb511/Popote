package com.team23.data.mappers

import com.team23.data.models.TagDataModel
import com.team23.domain.recipe.model.TagDomainModel

class TagMapper {
    fun toTagDomainModel(tags: List<TagDataModel>): List<TagDomainModel> = tags
        .map { it.label }
        .map { tag ->
            val region = tagCuisineRegion[tag]
            val localizedName = translateTagIfNeeded(tag)
            if (region != null) {
                TagDomainModel.CuisineRegion(region = region, localizedName = localizedName)
            } else {
                TagDomainModel.Normal(localizedName = localizedName)
            }
        }

    fun toTagDataModel(recipeId: String, tags: List<TagDomainModel>): List<TagDataModel> = tags
        .map { tag -> TagDataModel(recipeId = recipeId, label = tag.localizedName) }

    fun translateBackToEnglish(tag: String): List<String> = tagsTranslation
        .filter { translation -> translation.value.containsValue(tag) }
        .map { it.key } + tagsTranslation.map { it.key }.filter { it == tag }

    private fun translateTagIfNeeded(englishTag: String): String =
        tagsTranslation[englishTag]?.get("fr") ?: englishTag
}

private val tagsTranslation: Map<String, Map<String, String>> = mapOf(
    "alsacian" to mapOf("fr" to "alsacien"),
    "american" to mapOf("fr" to "américain"),
    "chinese" to mapOf("fr" to "chinois"),
    "dessert" to mapOf("fr" to "dessert"),
    "desert" to mapOf("fr" to "dessert"),
    "dish" to mapOf("fr" to "plat"),
    "drink" to mapOf("fr" to "boisson"),
    "fish" to mapOf("fr" to "poisson"),
    "german" to mapOf("fr" to "allemand"),
    "italian" to mapOf("fr" to "italien"),
    "main" to mapOf("fr" to "plat princal"),
    "pie" to mapOf("fr" to "tarte"),
    "quick" to mapOf("fr" to "rapide"),
    "rice" to mapOf("fr" to "riz"),
    "rum" to mapOf("fr" to "rhum"),
    "soup" to mapOf("fr" to "soupe"),
    "starter" to mapOf("fr" to "entrée"),
    "swiss" to mapOf("fr" to "suisse"),
    "tex-mex" to mapOf("fr" to "tex-mex"),
    "vegan" to mapOf("fr" to "vegan"),
    "vegetarian" to mapOf("fr" to "végétarien"),
    "veggie" to mapOf("fr" to "végétarien"),
)

private val tagCuisineRegion: Map<String, TagDomainModel.Region> = mapOf(
    "american" to TagDomainModel.Region.AMERICAN,
    "tex-mex" to TagDomainModel.Region.AMERICAN_MEXICAN,
    "alsacian" to TagDomainModel.Region.ALSATIAN,
    "alsatian" to TagDomainModel.Region.ALSATIAN,
    "chinese" to TagDomainModel.Region.CHINESE,
    "french" to TagDomainModel.Region.FRENCH,
    "hungarian" to TagDomainModel.Region.HUNGARIAN,
    "indian" to TagDomainModel.Region.INDIAN,
    "italian" to TagDomainModel.Region.ITALIAN,
    "norman" to TagDomainModel.Region.NORMAN,
    "thai" to TagDomainModel.Region.THAI,
    "turkish" to TagDomainModel.Region.TURKISH,
)