package com.team23.data.parsers

import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements
import com.team23.data.models.SummarizedRecipeDataModel

internal class SummarizedRecipeParser {
	fun toSummarizedRecipeDataModels(rawLatestPosts: Elements): List<SummarizedRecipeDataModel> =
		rawLatestPosts.map(::toSummarizedRecipeDataModel)

	private fun toSummarizedRecipeDataModel(rawLatestPost: Element) = SummarizedRecipeDataModel(
		href = rawLatestPost.select("a").attr("href"),
		imgSrc = rawLatestPost.select("img").attr("src"),
		title = rawLatestPost.select("div.recipe-preview-tile__title").text()
	).also { dataModel ->
		if (dataModel.href.isBlank() && dataModel.title.isBlank() && dataModel.imgSrc.isBlank()) {
			throw IllegalArgumentException()
		}
	}
}
