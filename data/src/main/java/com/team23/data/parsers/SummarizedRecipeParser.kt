package com.team23.data.parsers

import com.team23.data.models.SummarizedRecipeDataModel
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class SummarizedRecipeParser @Inject constructor() {
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
