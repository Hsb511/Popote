package com.team23.data.mappers

import com.team23.data.models.TagDataModel
import javax.inject.Inject

class TagMapper @Inject constructor() {
	fun toTagDomainModel(tags: List<TagDataModel>): List<String> = tags
		.map { translateTagIfNeeded(it.label) }

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
