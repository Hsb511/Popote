package com.example.data.mappers

import com.example.data.models.FullRecipeDataModel
import com.example.data.models.SummarizedRecipeDataModel
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class RawElementsMapper @Inject constructor() {
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

    fun toFullRecipeDataModel(recipeId: String, rawRecipe: Elements) = FullRecipeDataModel(
        href = recipeId,
        imgSrc = rawRecipe.select("img").attr("src"),
        title = rawRecipe.select("h1").text(),
        subTitle = rawRecipe.select("p").first()!!.text(),
        instructionTitle = rawRecipe
            .select("section.recipe__instructions")
            .html()
            .split("<h2>")
            .first()
            .split("<br>")
            .first(),
        lastTitle = rawRecipe
            .select("section.recipe__instructions")
            .html()
            .split("</div>")
            .last(),
    )
}
