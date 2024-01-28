package com.team23.data.parsers

import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements
import com.team23.data.models.SummarizedRecipeDataModel

internal class SummarizedRecipeParser {
	fun toSummarizedRecipeDataModels(rawLatestPosts: Elements): List<SummarizedRecipeDataModel> =
		rawLatestPosts.mapNotNull(::toSummarizedRecipeDataModel)

	private fun toSummarizedRecipeDataModel(rawLatestPost: Element): SummarizedRecipeDataModel? {
		val href = rawLatestPost.select("a").attr("href").ifBlank { return null }
		val imgSrc = rawLatestPost.select("img").attr("src").ifBlank { return null }
		val title = rawLatestPost.select("div.recipe-preview-tile__title").text().ifBlank { return null }

		return SummarizedRecipeDataModel(href, imgSrc, title)
	}
}
